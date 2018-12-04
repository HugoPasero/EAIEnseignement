package serviceenseignement;

import donnes.date.DateConvention;
import java.util.HashMap;
import donnes.preconvention.PreConvention;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Classe permettant de gérer un service enseignement comme décrit ci-dessous.
 * Chaque département d’enseignement propose un service de vérification « pédagogique » des
 * stages relevant de ses formations. Ainsi, pour chaque stage envisagé, associé à une formation,
 * ce service doit vérifier que le stage est bien inscrit dans le cursus de la formation diplômante,
 * que les activités décrites dans le résumé relèvent bien à la fois de la thématique de la formation
 * et du niveau de compétences adéquat. Une fois cette validation opérée, le service vérifie que la
 * durée envisagée est suffisante pour valider l’unité d’enseignement (UE) correspondante (par
 * exemple 5 mois minimum). Enfin, si tout est correct, le service désigne l’enseignant-chercheur
 * du département qui sera responsable du suivi de l’étudiant durant son stage et dont le nom sera
 * mentionné sur la convention. La validation pédagogique s’accompagne donc du nom du tuteur
 * universitaire.
 * Le service des stages devra ensuite informer le département de la validité du stage dès qu’aura
 * été décidé l’établissement de la convention.
 * @author marieroca
 */
public class ServiceEnseignement {
    private HashMap<Long, PreConvention> conv;
    private HashMap<Long, PreConvention> convTraitees;
    static MessageConsumer receiver = null;
    static MessageProducer sender = null;
    static Session session = null;

    /**
     * Construction du service enseignement qui réceptionne les préconvention envoyées par le serveur web
     * Et qui s'occuppe de vérifier :
     *      - que la durée du stage est supérieure à la durée min pour valider l'UE
     *      - que le stage (sujet, résumé des activités) corresponde bien inscrit dans la formation et
     *      qu'il corresponde à la thématique de la formation ainsi qu'au niveau d'étude de l'étudiant
     * Et qui donne un tuteur encadrant à l'étudiant pour sont stage
     * @param conv liste des préconvention en cours
     * @param convTraitees liste des préconventions traitées par le service enseignement (validées ou refusées)
     */
    public ServiceEnseignement(HashMap<Long, PreConvention> conv, HashMap<Long, PreConvention> convTraitees) {
        //Si la liste des préconvention en cours ou traitées sont nulles ont les instancie vides
        if (conv == null)
            this.conv = new HashMap();
        else
            this.conv = conv;
        
        if (convTraitees == null)
            this.convTraitees = new HashMap();
        else
            this.convTraitees = convTraitees;
        try {
            //On receptionne les préconvention envoyées par le serveur web
            this.recevoir();
        } catch (NamingException ex) {
            Logger.getLogger(ServiceEnseignement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Getter de la map des préconventions à traiter
     * @return la map(id, préconventions) des conventions à traiter
     */
    public HashMap<Long, PreConvention> getConv() {
        return conv;
    }

    /**
     * Setter de la map des préconventions à traiter
     * @param conv nouvelle map des préconventions à traiter
     */
    public void setConv(HashMap<Long, PreConvention> conv) {
        this.conv = conv;
    }

    /**
     * Getter de la map des préconventions traitées
     * @return la map(id, préconventions) des conventions traitées
     */
    public HashMap<Long, PreConvention> getConvTraitees() {
        return convTraitees;
    }

    /**
     * Setter de la map des préconventions traitées
     * @param conv nouvelle map des préconventions traitées
     */
    public void setConvTraitees(HashMap<Long, PreConvention> convTraitees) {
        this.convTraitees = convTraitees;
    }

    /**
     * Initialisation de la connexion : 
     *      - au topic "Pré-convention" pour réceptionner les préconvention envoyées par le serveur web
     *      - à la queue "Conventions en cours" pour envoyer les conventions traitées (validées ou refusées) au service des stages
     * @throws NamingException
     * @throws JMSException 
     */
    static public void init() throws NamingException, JMSException {
        System.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
        InitialContext context = new InitialContext();
        
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "jms/__defaultConnectionFactory";
        Destination dest = null;

        // look up the ConnectionFactory
        factory = (ConnectionFactory) context.lookup(factoryName);

        //Pour le receiver
        String destName = "PreConvention";
        
        // look up the Destination
        dest = (Destination) context.lookup(destName);

        // create the connection
        connection = factory.createConnection();

        // create the session
        session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

        // create the receiver
        //je veux recevoir toutes les conventions
        receiver = session.createConsumer(dest);

        //Pour le sender
        destName = "ConventionEnCours2";

        // look up the Destination
        dest = (Destination) context.lookup(destName);

        // create the sender
        sender = session.createProducer(dest);

        // start the connection, to enable message receipt
        connection.start();
    }
    
    /**
     * Méthode permettant de recevoir les messages JMS emits par le serveur web (préconventions à valider)
     * via le sujet de discussion "PreConvention"
     * @return map (id, pré convention)
     * @throws NamingException 
     */
    public void recevoir() throws NamingException {
        try {
            //Dégager le while true à terme
            while(true) {
                Message message = receiver.receiveNoWait();
                if (message instanceof ObjectMessage) {
                    //on récupère le message
                    ObjectMessage flux = (ObjectMessage) message;
                    //on récupère l'objet dans le message
                    Object preconvention = flux.getObject();
                    //on récupère la pré-conv dans l'objet
                    if (preconvention instanceof PreConvention) {
                        PreConvention convention = (PreConvention) preconvention;
                        if(!conv.containsKey(convention.getId()) && !convTraitees.containsKey(convention.getId()))
                            conv.put(convention.getId(), convention);
                        else 
                            break;
                    }
                } else if (message != null) {
                    System.out.println("Pas de préconvention reçue");
                }
            }
        } catch (JMSException exception) {
            exception.printStackTrace();
        }
    }
    
    /**
     * Méthode permettant d'envoyer les messages JMS (préconventions validées ou refusées) du service juridique
     * vers le service des stages via la file ConventionEnCours
     * @param pc préconvention à envoyer
     * @param validite statut de la validité de la préconvention
     * @throws NamingException
     * @throws InterruptedException 
     */
    public void envoyer(PreConvention pc) throws NamingException{
        try {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(pc);
            sender.send(message);
            
            System.out.println("Sent: " + message.getObject() + "\n est valide " + pc.estValide());
        } catch (JMSException ex) {
            Logger.getLogger(ServiceEnseignement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Méthode qui vérifie que la durée envisagée est suffisante pour valider l’unité d’enseignement (UE) correspondante (par
     * exemple 5 mois minimum)
     */
    /**
     * Méthode permettant de vérifier que la durée envisagée est suffisante pour valider l’unité 
     * d’enseignement (UE) correspondante (par exemple 5 mois minimum)
     * @param p préconvention où il faut vérifier la durée
     * @param nbMoisMin nombre de mois minimum pour valider l'UE
     * @return 
     */
    public boolean duree(PreConvention p, int nbMoisMin){
        DateConvention dDeb = p.getDateDeb();
        DateConvention dFin = p.getDateFin();
        if(DateConvention.nbMois(dDeb.getDate(), dFin.getDate()) >= nbMoisMin)
            return true;
        else
            return false;
    }

    /**
     * Méthode permettant d'affecter l’enseignant-chercheur du département qui 
     * sera responsable du suivi de l’étudiant durant son stage et dont le nom 
     * sera mentionné sur la convention
     * @param p préconvention où il faut affecter le tuteur
     * @param tuteur nom du tuteur a affecter
     */
    public void tuteur(PreConvention p, String tuteur){
        p.setTuteur(tuteur);
    }
}

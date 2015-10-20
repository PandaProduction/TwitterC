package dao.twitter;

import errorMessage.CodeError;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import view.component.PandaProdColor;

/**
 * Couche en communication avec Twitter
 *
 * @author Mary
 */
public class ConnectionTwitter {

    /**
     * Permet de créer une connexion avec Twitter.
     *
     * @param user Utilisateur qui sera connecté à Twitter.
     * @return code d'erreur
     */
    public static int connect(User user) {
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("VF0hA818WofbLoNe4W0IbHXrJ")
                    .setOAuthConsumerSecret("oIVeAvqPAMuEiHyuFVJxan56YNGtCfvASrr40EaitFZS8o8EJN")
                    .setOAuthAccessToken(null)
                    .setOAuthAccessTokenSecret(null);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            RequestToken requestToken = twitter.getOAuthRequestToken();
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            user.setTwitter(twitter);
            user.setRequestToken(requestToken);

            return CodeError.SUCESS;
        } catch (TwitterException | URISyntaxException | IOException ex) {
            System.err.println(ex);

            return CodeError.CONNEXION_FAIL;
        }

    }

    /**
     * Permet de vérifier la validité du code.
     *
     * @param user Utilisateur qui sera connecté.
     * @param code Code fournis par Twitter.
     * @return code d'erreur.
     */
    public static int codeValide(User user, String code) {
        try {
            user.getTwitter().getOAuthAccessToken(user.getRequestToken(), code);
            /*Set<String> lm = user.getTwitter().getRateLimitStatus().keySet();
             for (String s : lm) {
             System.out.println(s);//System.out.println(s + " : LIMITE : " +  user.getTwitter().getRateLimitStatus().get(s).getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get(s).getRemaining());
             }*/
            loadDataOnProfile(user);

            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }

    /**
     * Permet de charger les informations de profil de l'utilisateur. Nom et
     * surnom, description, date de création du compte, langue, lieu, site web,
     * image de profile et image de banière.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadDataOnProfile(User user) {
        try {
            user.setId(user.getTwitter().getId());
            twitter4j.User twitterUser = user.getTwitter().showUser(user.getId());
            user.setTwitterName(twitterUser.getScreenName());
            user.setName(twitterUser.getName());
            user.setDescritpion(twitterUser.getDescription());
            SimpleDateFormat dateformat = new SimpleDateFormat("MM-yyyy");
            user.setInscription(dateformat.format(twitterUser.getCreatedAt()));
            user.setLangue(twitterUser.getLang());
            user.setLocation(twitterUser.getLocation());
            user.setWebSite(twitterUser.getURLEntity().getExpandedURL());
            user.setProfile(twitterUser.getBiggerProfileImageURL());
            user.setBan(twitterUser.getProfileBannerURL());

            return CodeError.SUCESS;
        } catch (TwitterException | IllegalStateException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }

    /**
     * Permet de charger les nombre clé de l'utilisateur Nombre de tweet, nombre
     * d'abonnés et nombre d'abonnement.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadNumber(User user) {
        try {
            twitter4j.User twitterUser = user.getTwitter().showUser(user.getId());
            user.setNbTweet(twitterUser.getStatusesCount());
            user.setNbFollowers(twitterUser.getFollowersCount());
            user.setNbFriends(twitterUser.getFriendsCount());

        } catch (TwitterException | IllegalStateException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
        return CodeError.SUCESS;
    }

    /**
     * Permet de charger les tweets de l'utilisateur.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadMyTweet(User user) {
        try {
            user.setListOfMyTweet(user.getTwitter().getUserTimeline());
            for (int i = 1; i <= user.getNbTweet() / 20; i++) {
                user.getListOfMyTweet().addAll(user.getTwitter().getUserTimeline(new Paging(i)));
            }
            //System.err.println(user.getTwitter().getRateLimitStatus().get("/statuses/user_timeline").getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get("/statuses/user_timeline").getRemaining());

            return CodeError.SUCESS;
        } catch (TwitterException | IllegalStateException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }

    /**
     * Permet de charger les abonnées de l'utilisateur.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadMyFollowers(User user) {
        try {
            user.setListOfFollowers(user.getTwitter().getFollowersList(user.getId(), -1, 20));

            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }

    /**
     * Permet de charger les abonnements de l'utilisateur.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadMyFriends(User user) {
        try {
            user.setListOfFriends(user.getTwitter().getFriendsList(user.getId(), -1, 20));

            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }

    /**
     * Permet de charger l'actualité qui intérésse l'utilisateur.
     *
     * @param user Utilisateur connecté.
     * @return Code d'erreur.
     */
    public static int loadTimeLine(User user) {
        try {
            user.setListOfTweet(user.getTwitter().getHomeTimeline());
            /*for (int i = 1; i <= 4; i++) {
             user.getListOfTweet().addAll(user.getTwitter().getHomeTimeline(new Paging(i)));
             }*/
            //System.err.println(user.getTwitter().getRateLimitStatus().get("/statuses/home_timeline").getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get("/statuses/home_timeline").getRemaining());

            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            System.err.println(ex);

            return CodeError.FAILLURE;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.twitter;

import errorMessage.CodeError;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import model.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Lucas
 */
public class ConnectionTwitter {

    public static int connect(User user) {
        // Twitter connection and OAuth
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("VF0hA818WofbLoNe4W0IbHXrJ")
                    .setOAuthConsumerSecret("oIVeAvqPAMuEiHyuFVJxan56YNGtCfvASrr40EaitFZS8o8EJN")
                    .setOAuthAccessToken(null)
                    .setOAuthAccessTokenSecret(null);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
//            twitter.setOAuthConsumer("pykpyky", "plefechaat");
//            System.err.println(twitter.getScreenName());
            RequestToken requestToken = twitter.getOAuthRequestToken();
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            user.setTwitter(twitter);
            user.setRequestToken(requestToken);

            return CodeError.SUCESS;

        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.CONNEXION_FAIL;
        } catch (URISyntaxException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.CONNEXION_FAIL;
        } catch (IOException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.CONNEXION_FAIL;
        }

    }

    public static int codeValide(User user, String code) {
        try {
            user.getTwitter().getOAuthAccessToken(user.getRequestToken(), code);
            user.setId(user.getTwitter().getId());
            twitter4j.User twitterUser = user.getTwitter().showUser(user.getId());
            user.setTwitterName(twitterUser.getScreenName());
            user.setName(twitterUser.getName());
            user.setDescritpion(twitterUser.getDescription());
            Calendar date = Calendar.getInstance();
            SimpleDateFormat dateformat = new SimpleDateFormat("MM-yyyy");
            dateformat.format(twitterUser.getCreatedAt());
            user.setInscription(dateformat.format(twitterUser.getCreatedAt()));
            user.setLangue(twitterUser.getLang());
            user.setLastTweet(twitterUser.getStatus().getText());
            user.setLocation(twitterUser.getLocation());
            user.setNbTweet(twitterUser.getStatusesCount());
            user.setNbFollowers(twitterUser.getFollowersCount());
            user.setNbFriends(twitterUser.getFriendsCount());
            user.setWebSite(twitterUser.getURLEntity().getExpandedURL());
            user.setListOfTweet(user.getTwitter().getHomeTimeline());
            user.setListOfMyTweet(user.getTwitter().getUserTimeline());
            user.setProfile(twitterUser.getProfileImageURL());

        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
        return CodeError.SUCESS;
    }
}

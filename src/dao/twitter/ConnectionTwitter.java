/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.twitter;

import errorMessage.CodeError;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static int connectUser(User aThis) {
        // Twitter connection and OAuth
        try {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("lH6JSO5KsVrLDb0bpRjwRKz6J")
                    .setOAuthConsumerSecret("eO1DeOaZdKpXJc5kGEhbD9aWM2zqmOsPnLoMXQU4MOO6cc5FvW")
                    .setOAuthAccessToken(null)
                    .setOAuthAccessTokenSecret(null);
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
//            twitter.setOAuthConsumer("pykpyky", "plefechaat");
//            System.err.println(twitter.getScreenName());
            RequestToken requestToken = twitter.getOAuthRequestToken();
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            return CodeError.SUCESS;

        } catch (TwitterException e) {
            e.printStackTrace();
            return CodeError.CONNEXION_FAIL;
        } catch (URISyntaxException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.CONNEXION_FAIL;
        } catch (IOException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.CONNEXION_FAIL;
        }

    }

}

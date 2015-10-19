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
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import twitter4j.Paging;
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
        System.err.println("Connect ");
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
        System.err.println("code valide ");
        try {
            user.getTwitter().getOAuthAccessToken(user.getRequestToken(), code);
            Set<String> lm = user.getTwitter().getRateLimitStatus().keySet();
            for (String s : lm) {
                System.out.println(s);//System.out.println(s + " : LIMITE : " +  user.getTwitter().getRateLimitStatus().get(s).getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get(s).getRemaining());
            }

            loadDataOnProfile(user);
            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }

    public static int loadDataOnProfile(User user) {
        System.err.println("loadDataOnProfile ");
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
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }

    public static int loadNumber(User user) {
        System.err.println("loadNumber ");
        try {
            twitter4j.User twitterUser = user.getTwitter().showUser(user.getId());
            user.setNbTweet(twitterUser.getStatusesCount());
            user.setNbFollowers(twitterUser.getFollowersCount());
            user.setNbFriends(twitterUser.getFriendsCount());

        } catch (TwitterException | IllegalStateException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
        return CodeError.SUCESS;
    }

    public static int loadMyTweet(User user) {
        System.err.println("loadMyTweet ");
        try {
            user.setListOfMyTweet(user.getTwitter().getUserTimeline());
            for (int i = 1; i <= user.getNbTweet() / 20; i++) {
                user.getListOfMyTweet().addAll(user.getTwitter().getUserTimeline(new Paging(i)));
            }
            System.err.println(user.getTwitter().getRateLimitStatus().get("/statuses/user_timeline").getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get("/statuses/user_timeline").getRemaining());
            return CodeError.SUCESS;
        } catch (TwitterException | IllegalStateException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }

    public static int loadMyFollowers(User user) {
        System.err.println("loadMyFollowers");
        try {
            user.setListOfFollowers(user.getTwitter().getFollowersList(user.getId(), -1, 20));
            return CodeError.SUCESS;

        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }

    public static int loadMyFriends(User user) {
        System.err.println("loadMyFriends ");
        try {
            user.setListOfFriends(user.getTwitter().getFriendsList(user.getId(), -1, 20));
            return CodeError.SUCESS;

        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }

    public static int loadTimeLine(User user) {
        System.err.println("loadTimeLine ");
        try {
            user.setListOfTweet(user.getTwitter().getHomeTimeline());
            /*for (int i = 1; i <= 4; i++) {
                user.getListOfTweet().addAll(user.getTwitter().getHomeTimeline(new Paging(i)));
            }*/
            System.err.println(user.getTwitter().getRateLimitStatus().get("/statuses/home_timeline").getLimit() + " RESTANT : " + user.getTwitter().getRateLimitStatus().get("/statuses/home_timeline").getRemaining());

            return CodeError.SUCESS;
        } catch (TwitterException ex) {
            Logger.getLogger(ConnectionTwitter.class.getName()).log(Level.SEVERE, null, ex);
            return CodeError.FAILLURE;
        }
    }
}

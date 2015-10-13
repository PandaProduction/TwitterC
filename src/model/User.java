/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.twitter.ConnectionTwitter;
import java.util.ArrayList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.api.TimelinesResources;
import twitter4j.auth.RequestToken;

/**
 * Utilisateur de l'application Cookie Swipe Peut être un singleton ?
 *
 * @author Mary
 */
public class User {

    //Variable membre
    private long id;
    private String twitterName, name, location, descritpion,langue, urlPicture, lastTweet, inscription, webSite;
    private ResponseList<Status> listOfTweet, listOfMyTweet; 

    public ResponseList<Status> getListOfMyTweet() {
        return listOfMyTweet;
    }

    public void setListOfMyTweet(ResponseList<Status> listOfMyTweet) {
        this.listOfMyTweet = listOfMyTweet;
    }
    private Twitter twitter;
    private RequestToken requestToken;
    private int nbTweet, nbFollowers, nbFriends;

    
    public String getTwitterName() {
        return twitterName;
    }

    public void setTwitterName(String twitterName) {
        this.twitterName = twitterName;
    }

    public int getNbTweet() {
        return nbTweet;
    }

    public void setNbTweet(int nbTweet) {
        this.nbTweet = nbTweet;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public String getLastTweet() {
        return lastTweet;
    }

    public void setLastTweet(String lastTweet) {
        this.lastTweet = lastTweet;
    }

    public String getInscription() {
        return inscription;
    }

    public void setInscription(String inscription) {
        this.inscription = inscription;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public ResponseList<Status> getListOfTweet() {
        return listOfTweet;
    }

    public void setListOfTweet(ResponseList<Status> listOfTweet) {
        this.listOfTweet = listOfTweet;
    }

    public int getNbFollowers() {
        return nbFollowers;
    }

    public void setNbFollowers(int nbFollowers) {
        this.nbFollowers = nbFollowers;
    }

    public int getNbFriends() {
        return nbFriends;
    }

    public void setNbFriends(int nbFriends) {
        this.nbFriends = nbFriends;
    }

    //Constrcuteur
    /**
     * Constructeur par defaut
     */
    public User() {

    }

    public void connect() {
        ConnectionTwitter.connect(this);
    }

    //Getter & setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public RequestToken getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(RequestToken requestToken) {
        this.requestToken = requestToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //equals & hashcode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name;
    }

}

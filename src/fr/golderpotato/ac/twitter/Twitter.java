package fr.golderpotato.ac.twitter;

import fr.golderpotato.ac.Main;
import twitter4j.Friendship;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.security.SecureRandom;

/**
 * Created by Eliaz on 08/04/2017.
 */
public class Twitter {

    twitter4j.Twitter twitter;
    public String lastTweet;
    public String prefix;

    public void connect()throws Exception{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey(Main.getInstance().getConfig().getString("oauthconsummerkey"));
        cb.setOAuthConsumerSecret(Main.getInstance().getConfig().getString("oauthconsummerkeysecret"));
        cb.setOAuthAccessToken(Main.getInstance().getConfig().getString("accesstoken"));
        cb.setOAuthAccessTokenSecret(Main.getInstance().getConfig().getString("accesstokensecret"));
        TwitterFactory factory = new TwitterFactory(cb.build());
        twitter = factory.getInstance();
        prefix = Main.getInstance().getConfig().getString("twitter.prefix");
    }

    public void tweetExisting(String tweet){
        try {
            if(lastTweet != tweet){
                twitter.updateStatus("["+prefix+"-"+getRandomString(5)+"] "+tweet);
                lastTweet = tweet;
            }
        }catch (Exception e){
           e.printStackTrace();
        }
    }


    public void tweetUnique(String tweet){
        try{
            if(lastTweet != tweet){
                twitter.updateStatus("["+prefix+"] "+tweet);
                lastTweet = tweet;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getRandomString(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}

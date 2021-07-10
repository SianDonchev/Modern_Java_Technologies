package bg.sofia.uni.fmi.mjt.socialmedia;

import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;
import bg.sofia.uni.fmi.mjt.socialmedia.content.ContentByLikesAndCommentsComparator;
import bg.sofia.uni.fmi.mjt.socialmedia.content.Publication;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.UsernameAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.UsernameNotFoundException;
import bg.sofia.uni.fmi.mjt.socialmedia.user.ActiveUser;
import bg.sofia.uni.fmi.mjt.socialmedia.user.User;

import java.time.LocalDateTime;
import java.util.*;

public class EvilSocialInator implements SocialMediaInator {
    private Map<String, User> users;
    private Map<String, String> contentHolder;

    @Override
    public void register(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username is null");
        }
        if (users.containsKey(username)) {
            throw new UsernameAlreadyExistsException("User with that name already exist");
        }

        addUser(username);
    }

    @Override
    public String publishPost(String username, LocalDateTime publishedOn, String description) {
        if (username == null || publishedOn == null || description == null) {
            throw new IllegalArgumentException("Username, publish date or description is null");
        }
        ActiveUser userToPublish = users.get(username);
        if (userToPublish == null) {
            throw new IllegalArgumentException("Username not found");
        }

        return userToPublish.publishPost(publishedOn, description);
    }

    @Override
    public String publishStory(String username, LocalDateTime publishedOn, String description) {
        if (username == null || publishedOn == null || description == null) {
            throw new IllegalArgumentException("Username, publish date or description is null");
        }
        ActiveUser userToPublish = users.get(username);
        if (userToPublish == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return userToPublish.publishStory(publishedOn, description);
    }

    @Override
    public void like(String username, String id) {
        if(username == null) {
            throw new IllegalArgumentException("Username is null");
        }
        if(id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        ActiveUser userToLike = users.get(username);
        if(userToLike == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        userToLike.like(id);
        addLikeToPost(id);
    }

    @Override
    public void comment(String username, String text, String id) {
        if(username == null) {
            throw new IllegalArgumentException("Username is null");
        }
        if(id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if(text == null) {
            throw new IllegalArgumentException("Text for comment is null");
        }

        ActiveUser userToComment = users.get(username);
        if(userToComment == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        userToComment.comment(id,text);
        addCommentToPost(id, text);
    }

    @Override
    public Collection<Content> getNMostPopularContent(int n) {
        Comparator<Content> contentByLikesAndCommentsComparator = new ContentByLikesAndCommentsComparator();
        TreeSet<Publication> mostPopularContents = new TreeSet<>(contentByLikesAndCommentsComparator);
        Set<Map.Entry<String,User>> users = this.users.entrySet();
        for(Map.Entry<String,User> user  : users) {
            Collection<Publication> publicationsPerUser = user.getValue().getPublications();
            addPublicationsWhichAreNotExpiredToCollection(mostPopularContents,publicationsPerUser);
        }

        List<Publication> firstNMostPopularContents = new ArrayList<>(mostPopularContents);
        return Collections.unmodifiableList(firstNMostPopularContents.subList(0,n));
    }

    @Override
    public Collection<Content> getNMostRecentContent(String username, int n) {
        return null;
    }

    @Override
    public String getMostPopularUser() {
        return null;
    }

    @Override
    public Collection<Content> findContentByTag(String tag) {
        return null;
    }

    @Override
    public List<String> getActivityLog(String username) {
        return null;
    }

    private void addPublicationsWhichAreNotExpiredToCollection(Collection<Publication> publications, Collection<Publication> publicationsToAdd) {
        for(Publication publication : publicationsToAdd) {
            if(!publication.isExpired()) {
                publications.add(publication);
            }
        }
    }

    private void addUser(String username) {
        User newUser = new User(username);
        users.put(username, newUser);
    }

    private void addLikeToPost(String id) {
        String usernameForPostHolder = contentHolder.get(id);
        User userForPostHolder = users.get(usernameForPostHolder);

        Publication likedPublication = userForPostHolder.getPublication(id);
        if(likedPublication == null) {
            throw new ContentNotFoundException("Content not found");
        }
        likedPublication.addLike();
    }

    private void addCommentToPost(String id, String text){
        String usernameForPostHolder = contentHolder.get(id);
        User userForPostHolder = users.get(usernameForPostHolder);

        Publication commentedPublication = userForPostHolder.getPublication(id);
        if(commentedPublication == null) {
            throw new ContentNotFoundException("Content not found");
        }
        commentedPublication.addComment(text);
    }
}

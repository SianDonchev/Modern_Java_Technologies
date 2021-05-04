package bg.sofia.uni.fmi.mjt.socialmedia.user;

import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;
import bg.sofia.uni.fmi.mjt.socialmedia.content.Pair;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class User {
    LinkedHashMap<String, Pair<String, LocalDateTime>> publications;
}

package br.ufal.ic.p2.jackut.repositories;

import br.ufal.ic.p2.jackut.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String FILE_NAME = "Users.ser";

    public List<User> getUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = objectInputStream.readObject();
            if (obj instanceof List<?>) {
                return (List<User>) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveUsers(List<User> users) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Users.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

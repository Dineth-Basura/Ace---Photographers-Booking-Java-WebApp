package com.example.services;

import com.example.models.User;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private String filePath;

    public UserService(String filePath) {
        this.filePath = filePath;
    }

    public boolean registerUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail());
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
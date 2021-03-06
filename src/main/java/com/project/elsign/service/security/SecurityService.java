package com.project.elsign.service.security;

/**
 * Service for Security.
 *
 */

public interface SecurityService {

    /**
     * Function that returns username of an authorized user
     */
    String findLoggedInUsername();

    /**
     * Function that login {@link com.project.elsign.model.User} by username and password
     * @param username
     * @param password
     */
    void autoLogin(String username, String password);
}
package com.harbe.authservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Map;

/**
 * Utility class providing helper methods for OAuth2 authentication operations
 */

public class OAuth2Utils {

    public static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";

    /**
     * Validates and retrieves an authenticated OAuth2 client token
     * 
     * @param authentication The authentication object to validate
     * @return OAuth2ClientAuthenticationToken if client is authenticated
     * @throws OAuth2AuthenticationException if client is invalid or not
     *                                       authenticated
     */
    public static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(
            Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;

        // Check if the principal is an instance of OAuth2ClientAuthenticationToken
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass()))
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();

        // Verify the client is authenticated
        if (clientPrincipal != null && clientPrincipal.isAuthenticated())
            return clientPrincipal;

        // Throw exception if client validation fails
        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }

    /**
     * Converts HttpServletRequest parameters to MultiValueMap format
     * 
     * @param request The HTTP request containing parameters
     * @return MultiValueMap containing all request parameters
     */
    public static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        // Get parameter map from request
        Map<String, String[]> parametersMap = request.getParameterMap();
        // Create new MultiValueMap with same size as parameter map
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parametersMap.size());
        // Convert and add each parameter to the MultiValueMap
        parametersMap.forEach((key, values) -> {
            if (values.length > 0)
                Arrays.stream(values).forEach(value -> parameters.add(key, value));
        });
        return parameters;
    }

    /**
     * Creates and throws an OAuth2 authentication error
     * 
     * @param errorCode     The OAuth2 error code
     * @param parameterName Name of the parameter causing the error
     * @param errorUri      URI with additional error information
     * @throws OAuth2AuthenticationException with specified error details
     */
    public static void throwError(String errorCode, String parameterName, String errorUri) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
        throw new OAuth2AuthenticationException(error);
    }
}

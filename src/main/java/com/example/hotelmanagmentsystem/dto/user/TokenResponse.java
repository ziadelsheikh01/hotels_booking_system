package com.example.hotelmanagmentsystem.dto.user;


public class TokenResponse
{
   private String AccessToken ;
   private String RefreshToken;

    public TokenResponse(String accessToken, String refreshToken) {
        AccessToken = accessToken;
        RefreshToken = refreshToken;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        RefreshToken = refreshToken;
    }
}

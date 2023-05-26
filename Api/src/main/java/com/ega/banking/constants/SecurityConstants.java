package com.ega.banking.constants;

import java.util.concurrent.TimeUnit;

public class SecurityConstants {

    public static final String SECRET_KEY = "2A462D4A614E645267556B58703273357638792F413F4428472B4B6250655368";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final long TOKEN_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);;


    private SecurityConstants() {}
}

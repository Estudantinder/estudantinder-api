package org.estudantinder.features.commom;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "cloudinaryCredentials")
public interface CloudinaryCredentials {
    String cloudName();
    String apiKey();
    String apiSecret();
}

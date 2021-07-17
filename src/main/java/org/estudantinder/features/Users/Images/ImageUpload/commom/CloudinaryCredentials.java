package org.estudantinder.features.Users.Images.ImageUpload.commom;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "cloudinaryCredentials")
public interface CloudinaryCredentials {
    String cloudName();
    String apiKey();
    String apiSecret();
}

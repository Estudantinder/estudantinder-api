package org.estudantinder.features.commom;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.eclipse.microprofile.config.ConfigProvider;

import io.smallrye.config.SmallRyeConfig;

public class CloudinaryUtils {
    public static Cloudinary getCloudinary(){
        SmallRyeConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class);
        CloudinaryCredentials cloudinaryCredentials = config.getConfigMapping(CloudinaryCredentials.class);

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", cloudinaryCredentials.cloudName(),
                "api_key", cloudinaryCredentials.apiKey(), "api_secret", cloudinaryCredentials.apiSecret()));

        return cloudinary;
    }
    
}

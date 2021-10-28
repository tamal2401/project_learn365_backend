package org.learn365.user.service.impl;

import com.amazonaws.services.cloudfront.CloudFrontCookieSigner;
import com.amazonaws.services.cloudfront.util.SignerUtils;
import com.amazonaws.util.DateUtils;
import org.learn365.modal.user.SignedCookieResponse;
import org.learn365.user.service.SignedCookie;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Service
public class SignedCookieImpl implements SignedCookie {

    @Override
    public SignedCookieResponse generateSignedCookie() throws InvalidKeySpecException, IOException {
        CloudFrontCookieSigner.CookiesForCustomPolicy cookies2 =null;
        try {
            SignerUtils.Protocol protocol = SignerUtils.Protocol.https;
            String distributionDomain = "d3mt49qkg021q7.cloudfront.net";
            //File privateKeyFile = new File("/path/to/cfcurlCloud/rsa-private-key.der");
            File privateKeyFile = new File("D://personal-details/private-signed-key.der");
            String resourcePath = "/grade-8/biology.mp4";
            String keyPairId = "APKAI2NN55GXNV3DMVBQ";
            Date activeFrom = DateUtils.parseISO8601Date("2021-08-13T22:20:00.000Z");
            Date expiresOn = DateUtils.parseISO8601Date("2021-08-25T22:20:00.000Z");
            String ipRange = "0.0.0.0/0";

            cookies2 = CloudFrontCookieSigner.getCookiesForCustomPolicy(
                    protocol, distributionDomain, privateKeyFile, resourcePath,
                    keyPairId, expiresOn, activeFrom, ipRange);
        }catch(Exception e){
            e.printStackTrace();
        }

        return new SignedCookieResponse(cookies2.getPolicy().getValue(),cookies2.getKeyPairId().getValue(),cookies2.getSignature().getValue());
    }
}

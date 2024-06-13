package com.salmon.test.framework.helpers.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
public class EncodeUtils {
    public static final String DEFAULT_ENCODING = "UTF-8";
    private static final Logger LOG = LoggerFactory
        .getLogger(EncodeUtils.class);
    static BASE64Encoder enc = new BASE64Encoder();
    static BASE64Decoder dec = new BASE64Decoder();

    public static String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            log.error("Unable to encode :> "+e.getMessage());
        }
        return null;
    }

    public static String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            log.error("Unable to decode :> "+e.getMessage());
        }
        return null;
    }

    //Utility to encrypt the message
    @Test
    public void encodePassword() {
        String txt = "Summer2019";
        String encoded = base64encode(txt);
        LOG.info(" is encoded to: " + encoded + " and that is decoding to: " + (txt = base64decode(encoded)));
    }
}

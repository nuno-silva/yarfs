package a16.yarfs.ca.handlers.messages;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;

import java.security.GeneralSecurityException;
import java.security.Key;

/**
 * Created by Rodrigo Rato on 12/6/17.
 */
public class InitialKeyRequestMessage extends AbstractMessage {


    public InitialKeyRequestMessage(byte[] cipheredKs, byte[] cipheredTargetUserAndNonce, byte[] hash) throws JSONException {
        this.put("k", Base64.encodeBase64(cipheredKs));
        this.put("m4", Base64.encodeBase64String(cipheredTargetUserAndNonce));
        this.put("hash", Base64.encodeBase64String(hash));
    }

    public byte[] getCAPublicCipheredKs() throws JSONException {
        return Base64.decodeBase64(this.getString("k"));
    }


    public TargetUserAndNonce getTargetUserAndNonce(Key sessionKey) throws JSONException, GeneralSecurityException {
        return (TargetUserAndNonce) InitialKeyRequestMessage.getJSONObjectFromCipheredB64(this.getString("m4"), sessionKey);
    }

    public byte[] getHash() throws JSONException {
        return Base64.decodeBase64(this.getString("hash"));
    }
}
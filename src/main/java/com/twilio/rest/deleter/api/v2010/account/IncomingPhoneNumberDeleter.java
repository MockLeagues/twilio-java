/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.deleter.api.v2010.account;

import com.twilio.rest.deleter.Deleter;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.api.v2010.account.IncomingPhoneNumber;

public class IncomingPhoneNumberDeleter extends Deleter<IncomingPhoneNumber> {
    private String ownerAccountSid;
    private final String sid;

    /**
     * Construct a new IncomingPhoneNumberDeleter.
     * 
     * @param sid Delete by unique phone-number Sid
     */
    public IncomingPhoneNumberDeleter(final String sid) {
        this.sid = sid;
    }

    /**
     * Construct a new IncomingPhoneNumberDeleter.
     * 
     * @param ownerAccountSid The owner_account_sid
     * @param sid Delete by unique phone-number Sid
     */
    public IncomingPhoneNumberDeleter(final String ownerAccountSid, 
                                      final String sid) {
        this.ownerAccountSid = ownerAccountSid;
        this.sid = sid;
    }

    /**
     * Make the request to the Twilio API to perform the delete.
     * 
     * @param client TwilioRestClient with which to make the request
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public boolean execute(final TwilioRestClient client) {
        this.ownerAccountSid = this.ownerAccountSid == null ? client.getAccountSid() : this.ownerAccountSid;
        Request request = new Request(
            HttpMethod.DELETE,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.ownerAccountSid + "/IncomingPhoneNumbers/" + this.sid + ".json",
            client.getRegion()
        );
        
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("IncomingPhoneNumber delete failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }
        
            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }
        
        return response.getStatusCode() == 204;
    }
}
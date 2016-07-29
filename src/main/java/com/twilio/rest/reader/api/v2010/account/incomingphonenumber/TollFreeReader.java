/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.reader.api.v2010.account.incomingphonenumber;

import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.reader.Reader;
import com.twilio.rest.resource.Page;
import com.twilio.rest.resource.ResourceSet;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.api.v2010.account.incomingphonenumber.TollFree;

public class TollFreeReader extends Reader<TollFree> {
    private String ownerAccountSid;
    private Boolean beta;
    private String friendlyName;
    private com.twilio.rest.type.PhoneNumber phoneNumber;

    /**
     * Construct a new TollFreeReader.
     */
    public TollFreeReader() {
    }

    /**
     * Construct a new TollFreeReader.
     * 
     * @param ownerAccountSid The owner_account_sid
     */
    public TollFreeReader(final String ownerAccountSid) {
        this.ownerAccountSid = ownerAccountSid;
    }

    /**
     * The beta.
     * 
     * @param beta The beta
     * @return this
     */
    public TollFreeReader byBeta(final Boolean beta) {
        this.beta = beta;
        return this;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public TollFreeReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The phone_number.
     * 
     * @param phoneNumber The phone_number
     * @return this
     */
    public TollFreeReader byPhoneNumber(final com.twilio.rest.type.PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return TollFree ResourceSet
     */
    @Override
    public ResourceSet<TollFree> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return TollFree ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<TollFree> firstPage(final TwilioRestClient client) {
        this.ownerAccountSid = this.ownerAccountSid == null ? client.getAccountSid() : this.ownerAccountSid;
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.ownerAccountSid + "/IncomingPhoneNumbers/TollFree.json",
            client.getRegion()
        );
        
        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     * 
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<TollFree> nextPage(final Page<TollFree> page, 
                                   final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(TwilioRestClient.Domains.API.toString(), client.getRegion())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of TollFree Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<TollFree> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("TollFree read failed: Unable to connect to server");
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
        
        return Page.fromJson(
            "incoming_phone_numbers",
            response.getContent(),
            TollFree.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (beta != null) {
            request.addQueryParam("Beta", beta.toString());
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (phoneNumber != null) {
            request.addQueryParam("PhoneNumber", phoneNumber.toString());
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}
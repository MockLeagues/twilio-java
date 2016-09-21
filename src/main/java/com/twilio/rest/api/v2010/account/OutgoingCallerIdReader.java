/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.api.v2010.account;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class OutgoingCallerIdReader extends Reader<OutgoingCallerId> {
    private String accountSid;
    private com.twilio.type.PhoneNumber phoneNumber;
    private String friendlyName;

    /**
     * Construct a new OutgoingCallerIdReader.
     */
    public OutgoingCallerIdReader() {
    }

    /**
     * Construct a new OutgoingCallerIdReader.
     * 
     * @param accountSid The account_sid
     */
    public OutgoingCallerIdReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * Only show the caller id resource that exactly matches this phone number.
     * 
     * @param phoneNumber Filter by phone number
     * @return this
     */
    public OutgoingCallerIdReader byPhoneNumber(final com.twilio.type.PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Only show the caller id resource that exactly matches this name.
     * 
     * @param friendlyName Filter by friendly name
     * @return this
     */
    public OutgoingCallerIdReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return OutgoingCallerId ResourceSet
     */
    @Override
    public ResourceSet<OutgoingCallerId> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return OutgoingCallerId ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<OutgoingCallerId> firstPage(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.GET,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/OutgoingCallerIds.json",
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
    public Page<OutgoingCallerId> nextPage(final Page<OutgoingCallerId> page, 
                                           final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.API.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of OutgoingCallerId Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<OutgoingCallerId> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("OutgoingCallerId read failed: Unable to connect to server");
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
            "outgoing_caller_ids",
            response.getContent(),
            OutgoingCallerId.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (phoneNumber != null) {
            request.addQueryParam("PhoneNumber", phoneNumber.toString());
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}
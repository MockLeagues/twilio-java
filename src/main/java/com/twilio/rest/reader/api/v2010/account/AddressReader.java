/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.reader.api.v2010.account;

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
import com.twilio.rest.resource.api.v2010.account.Address;

public class AddressReader extends Reader<Address> {
    private String accountSid;
    private String customerName;
    private String friendlyName;
    private String isoCountry;

    /**
     * Construct a new AddressReader.
     */
    public AddressReader() {
    }

    /**
     * Construct a new AddressReader.
     * 
     * @param accountSid The account_sid
     */
    public AddressReader(final String accountSid) {
        this.accountSid = accountSid;
    }

    /**
     * The customer_name.
     * 
     * @param customerName The customer_name
     * @return this
     */
    public AddressReader byCustomerName(final String customerName) {
        this.customerName = customerName;
        return this;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public AddressReader byFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The iso_country.
     * 
     * @param isoCountry The iso_country
     * @return this
     */
    public AddressReader byIsoCountry(final String isoCountry) {
        this.isoCountry = isoCountry;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Address ResourceSet
     */
    @Override
    public ResourceSet<Address> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Address ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Address> firstPage(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/Addresses.json",
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
    public Page<Address> nextPage(final Page<Address> page, 
                                  final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(TwilioRestClient.Domains.API.toString(), client.getRegion())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Address Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Address> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Address read failed: Unable to connect to server");
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
            "addresses",
            response.getContent(),
            Address.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (customerName != null) {
            request.addQueryParam("CustomerName", customerName);
        }
        
        if (friendlyName != null) {
            request.addQueryParam("FriendlyName", friendlyName);
        }
        
        if (isoCountry != null) {
            request.addQueryParam("IsoCountry", isoCountry);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}
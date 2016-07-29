/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.reader.api.v2010.account.call;

import com.google.common.collect.Range;
import com.twilio.rest.converter.DateConverter;
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
import com.twilio.rest.resource.api.v2010.account.call.Recording;
import org.joda.time.DateTime;

public class RecordingReader extends Reader<Recording> {
    private String accountSid;
    private final String callSid;
    private DateTime absoluteDateCreated;
    private Range<DateTime> rangeDateCreated;

    /**
     * Construct a new RecordingReader.
     * 
     * @param callSid The call_sid
     */
    public RecordingReader(final String callSid) {
        this.callSid = callSid;
    }

    /**
     * Construct a new RecordingReader.
     * 
     * @param accountSid The account_sid
     * @param callSid The call_sid
     */
    public RecordingReader(final String accountSid, 
                           final String callSid) {
        this.accountSid = accountSid;
        this.callSid = callSid;
    }

    /**
     * The absolute_date_created.
     * 
     * @param absoluteDateCreated The absolute_date_created
     * @return this
     */
    public RecordingReader byDateCreated(final DateTime absoluteDateCreated) {
        this.rangeDateCreated = null;
        this.absoluteDateCreated = absoluteDateCreated;
        return this;
    }

    /**
     * The range_date_created.
     * 
     * @param rangeDateCreated The range_date_created
     * @return this
     */
    public RecordingReader byDateCreated(final Range<DateTime> rangeDateCreated) {
        this.absoluteDateCreated = null;
        this.rangeDateCreated = rangeDateCreated;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    public ResourceSet<Recording> execute(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage());
    }

    /**
     * Make the request to the Twilio API to perform the read.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Recording ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<Recording> firstPage(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.GET,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/Calls/" + this.callSid + "/Recordings.json",
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
    public Page<Recording> nextPage(final Page<Recording> page, 
                                    final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(TwilioRestClient.Domains.API.toString(), client.getRegion())
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of Recording Resources for a given request.
     * 
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<Recording> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Recording read failed: Unable to connect to server");
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
            "recordings",
            response.getContent(),
            Recording.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     * 
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (absoluteDateCreated != null) {
            request.addQueryParam("DateCreated", absoluteDateCreated.toString(Request.QUERY_STRING_DATE_FORMAT));
        } else if (rangeDateCreated != null) {
            request.addQueryDateRange("DateCreated", rangeDateCreated);
        }
        
        request.addQueryParam("PageSize", Integer.toString(getPageSize()));
    }
}
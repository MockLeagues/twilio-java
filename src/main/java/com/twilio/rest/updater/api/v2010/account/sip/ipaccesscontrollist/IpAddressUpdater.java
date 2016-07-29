/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.updater.api.v2010.account.sip.ipaccesscontrollist;

import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.api.v2010.account.sip.ipaccesscontrollist.IpAddress;
import com.twilio.rest.updater.Updater;

public class IpAddressUpdater extends Updater<IpAddress> {
    private String accountSid;
    private final String ipAccessControlListSid;
    private final String sid;
    private String ipAddress;
    private String friendlyName;

    /**
     * Construct a new IpAddressUpdater.
     * 
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     */
    public IpAddressUpdater(final String ipAccessControlListSid, 
                            final String sid) {
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.sid = sid;
    }

    /**
     * Construct a new IpAddressUpdater.
     * 
     * @param accountSid The account_sid
     * @param ipAccessControlListSid The ip_access_control_list_sid
     * @param sid The sid
     */
    public IpAddressUpdater(final String accountSid, 
                            final String ipAccessControlListSid, 
                            final String sid) {
        this.accountSid = accountSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
        this.sid = sid;
    }

    /**
     * The ip_address.
     * 
     * @param ipAddress The ip_address
     * @return this
     */
    public IpAddressUpdater setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public IpAddressUpdater setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Updated IpAddress
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public IpAddress execute(final TwilioRestClient client) {
        this.accountSid = this.accountSid == null ? client.getAccountSid() : this.accountSid;
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.accountSid + "/SIP/IpAccessControlLists/" + this.ipAccessControlListSid + "/IpAddresses/" + this.sid + ".json",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("IpAddress update failed: Unable to connect to server");
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
        
        return IpAddress.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (ipAddress != null) {
            request.addPostParam("IpAddress", ipAddress);
        }
        
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
    }
}
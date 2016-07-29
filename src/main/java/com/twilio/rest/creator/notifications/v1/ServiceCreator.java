/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /       
 */

package com.twilio.rest.creator.notifications.v1;

import com.twilio.rest.creator.Creator;
import com.twilio.rest.exception.ApiConnectionException;
import com.twilio.rest.exception.ApiException;
import com.twilio.rest.http.HttpMethod;
import com.twilio.rest.http.Request;
import com.twilio.rest.http.Response;
import com.twilio.rest.http.TwilioRestClient;
import com.twilio.rest.resource.RestException;
import com.twilio.rest.resource.notifications.v1.Service;

public class ServiceCreator extends Creator<Service> {
    private String friendlyName;
    private String apnCredentialSid;
    private String gcmCredentialSid;
    private String messagingServiceSid;
    private String facebookMessengerPageId;
    private String defaultApnNotificationProtocolVersion;
    private String defaultGcmNotificationProtocolVersion;

    /**
     * The friendly_name.
     * 
     * @param friendlyName The friendly_name
     * @return this
     */
    public ServiceCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The apn_credential_sid.
     * 
     * @param apnCredentialSid The apn_credential_sid
     * @return this
     */
    public ServiceCreator setApnCredentialSid(final String apnCredentialSid) {
        this.apnCredentialSid = apnCredentialSid;
        return this;
    }

    /**
     * The gcm_credential_sid.
     * 
     * @param gcmCredentialSid The gcm_credential_sid
     * @return this
     */
    public ServiceCreator setGcmCredentialSid(final String gcmCredentialSid) {
        this.gcmCredentialSid = gcmCredentialSid;
        return this;
    }

    /**
     * The messaging_service_sid.
     * 
     * @param messagingServiceSid The messaging_service_sid
     * @return this
     */
    public ServiceCreator setMessagingServiceSid(final String messagingServiceSid) {
        this.messagingServiceSid = messagingServiceSid;
        return this;
    }

    /**
     * The facebook_messenger_page_id.
     * 
     * @param facebookMessengerPageId The facebook_messenger_page_id
     * @return this
     */
    public ServiceCreator setFacebookMessengerPageId(final String facebookMessengerPageId) {
        this.facebookMessengerPageId = facebookMessengerPageId;
        return this;
    }

    /**
     * The default_apn_notification_protocol_version.
     * 
     * @param defaultApnNotificationProtocolVersion The
     *                                              default_apn_notification_protocol_version
     * @return this
     */
    public ServiceCreator setDefaultApnNotificationProtocolVersion(final String defaultApnNotificationProtocolVersion) {
        this.defaultApnNotificationProtocolVersion = defaultApnNotificationProtocolVersion;
        return this;
    }

    /**
     * The default_gcm_notification_protocol_version.
     * 
     * @param defaultGcmNotificationProtocolVersion The
     *                                              default_gcm_notification_protocol_version
     * @return this
     */
    public ServiceCreator setDefaultGcmNotificationProtocolVersion(final String defaultGcmNotificationProtocolVersion) {
        this.defaultGcmNotificationProtocolVersion = defaultGcmNotificationProtocolVersion;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     * 
     * @param client TwilioRestClient with which to make the request
     * @return Created Service
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Service execute(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            TwilioRestClient.Domains.NOTIFICATIONS.toString(),
            "/v1/Services",
            client.getRegion()
        );
        
        addPostParams(request);
        Response response = client.request(request);
        
        if (response == null) {
            throw new ApiConnectionException("Service creation failed: Unable to connect to server");
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
        
        return Service.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     * 
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }
        
        if (apnCredentialSid != null) {
            request.addPostParam("ApnCredentialSid", apnCredentialSid);
        }
        
        if (gcmCredentialSid != null) {
            request.addPostParam("GcmCredentialSid", gcmCredentialSid);
        }
        
        if (messagingServiceSid != null) {
            request.addPostParam("MessagingServiceSid", messagingServiceSid);
        }
        
        if (facebookMessengerPageId != null) {
            request.addPostParam("FacebookMessengerPageId", facebookMessengerPageId);
        }
        
        if (defaultApnNotificationProtocolVersion != null) {
            request.addPostParam("DefaultApnNotificationProtocolVersion", defaultApnNotificationProtocolVersion);
        }
        
        if (defaultGcmNotificationProtocolVersion != null) {
            request.addPostParam("DefaultGcmNotificationProtocolVersion", defaultGcmNotificationProtocolVersion);
        }
    }
}
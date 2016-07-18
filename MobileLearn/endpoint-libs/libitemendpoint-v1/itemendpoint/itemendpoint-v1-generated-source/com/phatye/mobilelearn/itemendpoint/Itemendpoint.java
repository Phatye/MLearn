/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-06-09 16:41:44 UTC)
 * on 2014-06-16 at 12:41:34 UTC 
 * Modify at your own risk.
 */

package com.phatye.mobilelearn.itemendpoint;

/**
 * Service definition for Itemendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ItemendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Itemendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the itemendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://hikmamlearn.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "itemendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Itemendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Itemendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link GetItem#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public GetItem getItem(java.lang.Long id) throws java.io.IOException {
    GetItem result = new GetItem(id);
    initialize(result);
    return result;
  }

  public class GetItem extends ItemendpointRequest<com.phatye.mobilelearn.itemendpoint.model.Item> {

    private static final String REST_PATH = "item/{id}";

    /**
     * Create a request for the method "getItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link GetItem#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetItem#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)} must
     * be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetItem(java.lang.Long id) {
      super(Itemendpoint.this, "GET", REST_PATH, null, com.phatye.mobilelearn.itemendpoint.model.Item.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetItem setAlt(java.lang.String alt) {
      return (GetItem) super.setAlt(alt);
    }

    @Override
    public GetItem setFields(java.lang.String fields) {
      return (GetItem) super.setFields(fields);
    }

    @Override
    public GetItem setKey(java.lang.String key) {
      return (GetItem) super.setKey(key);
    }

    @Override
    public GetItem setOauthToken(java.lang.String oauthToken) {
      return (GetItem) super.setOauthToken(oauthToken);
    }

    @Override
    public GetItem setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetItem setQuotaUser(java.lang.String quotaUser) {
      return (GetItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetItem setUserIp(java.lang.String userIp) {
      return (GetItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetItem setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetItem set(String parameterName, Object value) {
      return (GetItem) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "getItems".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link GetItems#execute()} method to invoke the remote operation.
   *
   * @param category
   * @param type
   * @return the request
   */
  public GetItems getItems(java.lang.String category, java.lang.String type) throws java.io.IOException {
    GetItems result = new GetItems(category, type);
    initialize(result);
    return result;
  }

  public class GetItems extends ItemendpointRequest<com.phatye.mobilelearn.itemendpoint.model.CollectionResponseItem> {

    private static final String REST_PATH = "collectionresponse_item/{category}/{type}";

    /**
     * Create a request for the method "getItems".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link GetItems#execute()} method to invoke the remote operation.
     * <p> {@link
     * GetItems#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param category
     * @param type
     * @since 1.13
     */
    protected GetItems(java.lang.String category, java.lang.String type) {
      super(Itemendpoint.this, "GET", REST_PATH, null, com.phatye.mobilelearn.itemendpoint.model.CollectionResponseItem.class);
      this.category = com.google.api.client.util.Preconditions.checkNotNull(category, "Required parameter category must be specified.");
      this.type = com.google.api.client.util.Preconditions.checkNotNull(type, "Required parameter type must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetItems setAlt(java.lang.String alt) {
      return (GetItems) super.setAlt(alt);
    }

    @Override
    public GetItems setFields(java.lang.String fields) {
      return (GetItems) super.setFields(fields);
    }

    @Override
    public GetItems setKey(java.lang.String key) {
      return (GetItems) super.setKey(key);
    }

    @Override
    public GetItems setOauthToken(java.lang.String oauthToken) {
      return (GetItems) super.setOauthToken(oauthToken);
    }

    @Override
    public GetItems setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetItems) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetItems setQuotaUser(java.lang.String quotaUser) {
      return (GetItems) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetItems setUserIp(java.lang.String userIp) {
      return (GetItems) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String category;

    /**

     */
    public java.lang.String getCategory() {
      return category;
    }

    public GetItems setCategory(java.lang.String category) {
      this.category = category;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String type;

    /**

     */
    public java.lang.String getType() {
      return type;
    }

    public GetItems setType(java.lang.String type) {
      this.type = type;
      return this;
    }

    @Override
    public GetItems set(String parameterName, Object value) {
      return (GetItems) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link InsertItem#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.phatye.mobilelearn.itemendpoint.model.Item}
   * @return the request
   */
  public InsertItem insertItem(com.phatye.mobilelearn.itemendpoint.model.Item content) throws java.io.IOException {
    InsertItem result = new InsertItem(content);
    initialize(result);
    return result;
  }

  public class InsertItem extends ItemendpointRequest<com.phatye.mobilelearn.itemendpoint.model.Item> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "insertItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link InsertItem#execute()} method to invoke the remote
     * operation. <p> {@link
     * InsertItem#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.phatye.mobilelearn.itemendpoint.model.Item}
     * @since 1.13
     */
    protected InsertItem(com.phatye.mobilelearn.itemendpoint.model.Item content) {
      super(Itemendpoint.this, "POST", REST_PATH, content, com.phatye.mobilelearn.itemendpoint.model.Item.class);
    }

    @Override
    public InsertItem setAlt(java.lang.String alt) {
      return (InsertItem) super.setAlt(alt);
    }

    @Override
    public InsertItem setFields(java.lang.String fields) {
      return (InsertItem) super.setFields(fields);
    }

    @Override
    public InsertItem setKey(java.lang.String key) {
      return (InsertItem) super.setKey(key);
    }

    @Override
    public InsertItem setOauthToken(java.lang.String oauthToken) {
      return (InsertItem) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertItem setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertItem setQuotaUser(java.lang.String quotaUser) {
      return (InsertItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertItem setUserIp(java.lang.String userIp) {
      return (InsertItem) super.setUserIp(userIp);
    }

    @Override
    public InsertItem set(String parameterName, Object value) {
      return (InsertItem) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link ListItem#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListItem listItem() throws java.io.IOException {
    ListItem result = new ListItem();
    initialize(result);
    return result;
  }

  public class ListItem extends ItemendpointRequest<com.phatye.mobilelearn.itemendpoint.model.CollectionResponseItem> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "listItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link ListItem#execute()} method to invoke the remote operation.
     * <p> {@link
     * ListItem#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListItem() {
      super(Itemendpoint.this, "GET", REST_PATH, null, com.phatye.mobilelearn.itemendpoint.model.CollectionResponseItem.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListItem setAlt(java.lang.String alt) {
      return (ListItem) super.setAlt(alt);
    }

    @Override
    public ListItem setFields(java.lang.String fields) {
      return (ListItem) super.setFields(fields);
    }

    @Override
    public ListItem setKey(java.lang.String key) {
      return (ListItem) super.setKey(key);
    }

    @Override
    public ListItem setOauthToken(java.lang.String oauthToken) {
      return (ListItem) super.setOauthToken(oauthToken);
    }

    @Override
    public ListItem setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListItem setQuotaUser(java.lang.String quotaUser) {
      return (ListItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListItem setUserIp(java.lang.String userIp) {
      return (ListItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListItem setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListItem setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListItem set(String parameterName, Object value) {
      return (ListItem) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link RemoveItem#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveItem removeItem(java.lang.Long id) throws java.io.IOException {
    RemoveItem result = new RemoveItem(id);
    initialize(result);
    return result;
  }

  public class RemoveItem extends ItemendpointRequest<Void> {

    private static final String REST_PATH = "item/{id}";

    /**
     * Create a request for the method "removeItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link RemoveItem#execute()} method to invoke the remote
     * operation. <p> {@link
     * RemoveItem#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveItem(java.lang.Long id) {
      super(Itemendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveItem setAlt(java.lang.String alt) {
      return (RemoveItem) super.setAlt(alt);
    }

    @Override
    public RemoveItem setFields(java.lang.String fields) {
      return (RemoveItem) super.setFields(fields);
    }

    @Override
    public RemoveItem setKey(java.lang.String key) {
      return (RemoveItem) super.setKey(key);
    }

    @Override
    public RemoveItem setOauthToken(java.lang.String oauthToken) {
      return (RemoveItem) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveItem setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveItem setQuotaUser(java.lang.String quotaUser) {
      return (RemoveItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveItem setUserIp(java.lang.String userIp) {
      return (RemoveItem) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveItem setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveItem set(String parameterName, Object value) {
      return (RemoveItem) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateItem".
   *
   * This request holds the parameters needed by the the itemendpoint server.  After setting any
   * optional parameters, call the {@link UpdateItem#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.phatye.mobilelearn.itemendpoint.model.Item}
   * @return the request
   */
  public UpdateItem updateItem(com.phatye.mobilelearn.itemendpoint.model.Item content) throws java.io.IOException {
    UpdateItem result = new UpdateItem(content);
    initialize(result);
    return result;
  }

  public class UpdateItem extends ItemendpointRequest<com.phatye.mobilelearn.itemendpoint.model.Item> {

    private static final String REST_PATH = "item";

    /**
     * Create a request for the method "updateItem".
     *
     * This request holds the parameters needed by the the itemendpoint server.  After setting any
     * optional parameters, call the {@link UpdateItem#execute()} method to invoke the remote
     * operation. <p> {@link
     * UpdateItem#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.phatye.mobilelearn.itemendpoint.model.Item}
     * @since 1.13
     */
    protected UpdateItem(com.phatye.mobilelearn.itemendpoint.model.Item content) {
      super(Itemendpoint.this, "PUT", REST_PATH, content, com.phatye.mobilelearn.itemendpoint.model.Item.class);
    }

    @Override
    public UpdateItem setAlt(java.lang.String alt) {
      return (UpdateItem) super.setAlt(alt);
    }

    @Override
    public UpdateItem setFields(java.lang.String fields) {
      return (UpdateItem) super.setFields(fields);
    }

    @Override
    public UpdateItem setKey(java.lang.String key) {
      return (UpdateItem) super.setKey(key);
    }

    @Override
    public UpdateItem setOauthToken(java.lang.String oauthToken) {
      return (UpdateItem) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateItem setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateItem) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateItem setQuotaUser(java.lang.String quotaUser) {
      return (UpdateItem) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateItem setUserIp(java.lang.String userIp) {
      return (UpdateItem) super.setUserIp(userIp);
    }

    @Override
    public UpdateItem set(String parameterName, Object value) {
      return (UpdateItem) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Itemendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Itemendpoint}. */
    @Override
    public Itemendpoint build() {
      return new Itemendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ItemendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setItemendpointRequestInitializer(
        ItemendpointRequestInitializer itemendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(itemendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}

package org.whispersystems.signalservice.internal.configuration;



import java.net.ProxySelector;
import java.util.List;
import java.util.Map;

import javax.net.SocketFactory;

import okhttp3.Dns;
import okhttp3.Interceptor;

public final class SignalServiceConfiguration {

  private final SignalServiceUrl[]           signalServiceUrls;
  private final Map<Integer, SignalCdnUrl[]> signalCdnUrlMap;
  private final SignalContactDiscoveryUrl[]  signalContactDiscoveryUrls;
  private final SignalCdsiUrl[]              signalCdsiUrls;
  private final SignalKeyBackupServiceUrl[]  signalKeyBackupServiceUrls;
  private final SignalStorageUrl[]           signalStorageUrls;
  private final List<Interceptor>            networkInterceptors;
  private final SocketFactory                socketFactory;
  private final ProxySelector                proxySelector;
  private final Dns                          dns;
  private final byte[]                       zkGroupServerPublicParams;
  private final boolean                      supportsWebSocket;

  public SignalServiceConfiguration(SignalServiceUrl[] signalServiceUrls,
                                    Map<Integer, SignalCdnUrl[]> signalCdnUrlMap,
                                    SignalContactDiscoveryUrl[] signalContactDiscoveryUrls,
                                    SignalKeyBackupServiceUrl[] signalKeyBackupServiceUrls,
                                    SignalStorageUrl[] signalStorageUrls,
                                    SignalCdsiUrl[] signalCdsiUrls,
                                    List<Interceptor> networkInterceptors,
                                    SocketFactory socketFactory,
                                    ProxySelector proxySelector,
                                    Dns dns,
                                    byte[] zkGroupServerPublicParams,
                                    boolean supportsWebSocket)
  {
    this.signalServiceUrls          = signalServiceUrls;
    this.signalCdnUrlMap            = signalCdnUrlMap;
    this.signalContactDiscoveryUrls = signalContactDiscoveryUrls;
    this.signalCdsiUrls             = signalCdsiUrls;
    this.signalKeyBackupServiceUrls = signalKeyBackupServiceUrls;
    this.signalStorageUrls          = signalStorageUrls;
    this.networkInterceptors        = networkInterceptors;
    this.socketFactory              = socketFactory;
    this.proxySelector              = proxySelector;
    this.dns                        = dns;
    this.zkGroupServerPublicParams  = zkGroupServerPublicParams;
    this.supportsWebSocket          = supportsWebSocket;
  }

  public SignalServiceUrl[] getSignalServiceUrls() {
    return signalServiceUrls;
  }

  public Map<Integer, SignalCdnUrl[]> getSignalCdnUrlMap() {
    return signalCdnUrlMap;
  }

  public SignalContactDiscoveryUrl[] getSignalContactDiscoveryUrls() {
    return signalContactDiscoveryUrls;
  }

  public SignalCdsiUrl[] getSignalCdsiUrls() {
    return signalCdsiUrls;
  }

  public SignalKeyBackupServiceUrl[] getSignalKeyBackupServiceUrls() {
    return signalKeyBackupServiceUrls;
  }

  public SignalStorageUrl[] getSignalStorageUrls() {
    return signalStorageUrls;
  }

  public List<Interceptor> getNetworkInterceptors() {
    return networkInterceptors;
  }

  public SocketFactory getSocketFactory() {
    return socketFactory;
  }

  public ProxySelector getProxySelector() {
    return proxySelector;
  }

  public Dns getDns() {
    return dns;
  }

  public byte[] getZkGroupServerPublicParams() {
    return zkGroupServerPublicParams;
  }

  public boolean supportsWebSockets() {
    return supportsWebSocket;
  }
}

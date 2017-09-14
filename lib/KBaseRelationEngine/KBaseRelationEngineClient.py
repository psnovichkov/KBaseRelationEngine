# -*- coding: utf-8 -*-
############################################################
#
# Autogenerated by the KBase type compiler -
# any changes made here will be overwritten
#
############################################################

from __future__ import print_function
# the following is a hack to get the baseclient to import whether we're in a
# package or not. This makes pep8 unhappy hence the annotations.
try:
    # baseclient and this client are in a package
    from .baseclient import BaseClient as _BaseClient  # @UnusedImport
except:
    # no they aren't
    from baseclient import BaseClient as _BaseClient  # @Reimport


class KBaseRelationEngine(object):

    def __init__(
            self, url=None, timeout=30 * 60, user_id=None,
            password=None, token=None, ignore_authrc=False,
            trust_all_ssl_certificates=False,
            auth_svc='https://kbase.us/services/authorization/Sessions/Login'):
        if url is None:
            raise ValueError('A url is required')
        self._service_ver = None
        self._client = _BaseClient(
            url, timeout=timeout, user_id=user_id, password=password,
            token=token, ignore_authrc=ignore_authrc,
            trust_all_ssl_certificates=trust_all_ssl_certificates,
            auth_svc=auth_svc)

    def initReferenceData(self, context=None):
        return self._client.call_method(
            'KBaseRelationEngine.initReferenceData',
            [], self._service_ver, context)

    def getFeatureSequences(self, params, context=None):
        """
        :param params: instance of type "GetFeatureSequencesParams" (* One of
           guids should provided.) -> structure: parameter "taxonomy_guid" of
           String, parameter "ortholog_guid" of String, parameter
           "goterm_guid" of String
        :returns: instance of list of type "FeatureSequence" -> structure:
           parameter "taxonomy_guid" of String, parameter "feature_guid" of
           String, parameter "proteinSequence" of String, parameter
           "nucleotideSequence" of String
        """
        return self._client.call_method(
            'KBaseRelationEngine.getFeatureSequences',
            [params], self._service_ver, context)

    def getCompendiumDescriptors(self, params, context=None):
        """
        :param params: instance of type "GetCompendiumDescriptorsParams" (*
           data_type - one of ["expression","fitness"]) -> structure:
           parameter "taxonomy_guid" of String, parameter "data_type" of
           String
        :returns: instance of list of type "CompendiumDescriptor" ->
           structure: parameter "guid" of String, parameter "name" of String,
           parameter "data_type" of String, parameter "taxonomy_guid" of
           String, parameter "ws_ndarray_id" of String
        """
        return self._client.call_method(
            'KBaseRelationEngine.getCompendiumDescriptors',
            [params], self._service_ver, context)

    def storeKEAppDescriptor(self, params, context=None):
        """
        :param params: instance of type "StoreKEAppDescriptorParams" ->
           structure: parameter "keapp" of type "KEAppDescriptor" ->
           structure: parameter "guid" of String, parameter "name" of String,
           parameter "version" of String, parameter "last_run_epoch" of Long,
           parameter "nodes_created" of Long, parameter "relations_created"
           of Long, parameter "properties_set" of Long
        """
        return self._client.call_method(
            'KBaseRelationEngine.storeKEAppDescriptor',
            [params], self._service_ver, context)

    def storeBiclusters(self, params, context=None):
        """
        :param params: instance of type "StoreBiclustersParams" -> structure:
           parameter "biclusters" of list of type "Bicluster" -> structure:
           parameter "guid" of String, parameter "keapp_guid" of String,
           parameter "compendium_guid" of String, parameter "feature_guids"
           of list of String, parameter "condition_guids" of list of String
        """
        return self._client.call_method(
            'KBaseRelationEngine.storeBiclusters',
            [params], self._service_ver, context)

    def status(self, context=None):
        return self._client.call_method('KBaseRelationEngine.status',
                                        [], self._service_ver, context)
package kbaserelationengine;


import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;


//BEGIN_HEADER
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//END_HEADER

/**
 * <p>Original spec-file module name: KBaseRelationEngine</p>
 * <pre>
 * A KBase module: KBaseRelationEngine
 * </pre>
 */
public class KBaseRelationEngineServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "https://github.com/psnovichkov/KBaseRelationEngine.git";
    private static final String gitCommitHash = "09f902c230b05769e5837e38fe7358d6eff52ec3";

    //BEGIN_CLASS_HEADER
    Set<String> admins  = new HashSet<String>();
    Neo4jDataProvider dataProvider;
    
    private void checkAdmin(AuthToken authPart) {
    	if( !admins.contains(authPart.getUserName()) ){
    		new IllegalStateException("User " + authPart.getUserName() + " should be admin to perform this operation");
    	}
	}
    
    //END_CLASS_HEADER

    public KBaseRelationEngineServer() throws Exception {
        super("KBaseRelationEngine");
        //BEGIN_CONSTRUCTOR
        admins.addAll(Arrays.asList(config.get("admins").split(",")));
        dataProvider = new Neo4jDataProvider(config);
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: getFeatureSequences</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.GetFeatureSequencesParams GetFeatureSequencesParams}
     * @return   instance of list of type {@link kbaserelationengine.FeatureSequence FeatureSequence}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.getFeatureSequences", async=true)
    public List<FeatureSequence> getFeatureSequences(GetFeatureSequencesParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<FeatureSequence> returnVal = null;
        //BEGIN getFeatureSequences
        returnVal = dataProvider.getFeatureSequences(params);
        //END getFeatureSequences
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: getCompendiumDescriptors</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.GetCompendiumDescriptorsParams GetCompendiumDescriptorsParams}
     * @return   instance of list of type {@link kbaserelationengine.CompendiumDescriptor CompendiumDescriptor}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.getCompendiumDescriptors", async=true)
    public List<CompendiumDescriptor> getCompendiumDescriptors(GetCompendiumDescriptorsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<CompendiumDescriptor> returnVal = null;
        //BEGIN getCompendiumDescriptors
        returnVal = dataProvider.getCompendiumDescriptors(params);
        //END getCompendiumDescriptors
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: storeKEAppDescriptor</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.StoreKEAppDescriptorParams StoreKEAppDescriptorParams}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.storeKEAppDescriptor", async=true)
    public void storeKEAppDescriptor(StoreKEAppDescriptorParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        //BEGIN storeKEAppDescriptor
    	checkAdmin(authPart);
    	dataProvider.storeKEAppDescriptor(params);
        //END storeKEAppDescriptor
    }


	/**
     * <p>Original spec-file function name: storeBiclusters</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.StoreBiclustersParams StoreBiclustersParams}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.storeBiclusters", async=true)
    public void storeBiclusters(StoreBiclustersParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        //BEGIN storeBiclusters
    	checkAdmin(authPart);    	
    	dataProvider.storeBiclusters(params);
        //END storeBiclusters
    }

    /**
     * <p>Original spec-file function name: getBiclusterDescriptors</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.GetBiclusterDescriptorsParams GetBiclusterDescriptorsParams}
     * @return   instance of list of type {@link kbaserelationengine.BiclusterDescriptor BiclusterDescriptor}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.getBiclusterDescriptors", async=true)
    public List<BiclusterDescriptor> getBiclusterDescriptors(GetBiclusterDescriptorsParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<BiclusterDescriptor> returnVal = null;
        //BEGIN getBiclusterDescriptors
        returnVal = dataProvider.getBiclusterDescriptors(params);
        //END getBiclusterDescriptors
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: getBiclusters</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link kbaserelationengine.GetBiclustersParams GetBiclustersParams}
     * @return   instance of list of type {@link kbaserelationengine.Bicluster Bicluster}
     */
    @JsonServerMethod(rpc = "KBaseRelationEngine.getBiclusters", async=true)
    public List<Bicluster> getBiclusters(GetBiclustersParams params, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        List<Bicluster> returnVal = null;
        //BEGIN getBiclusters
        returnVal = dataProvider.getBiclusters(params);        
        //END getBiclusters
        return returnVal;
    }
    @JsonServerMethod(rpc = "KBaseRelationEngine.status")
    public Map<String, Object> status() {
        Map<String, Object> returnVal = null;
        //BEGIN_STATUS
        returnVal = new LinkedHashMap<String, Object>();
        returnVal.put("state", "OK");
        returnVal.put("message", "");
        returnVal.put("version", version);
        returnVal.put("git_url", gitUrl);
        returnVal.put("git_commit_hash", gitCommitHash);
        //END_STATUS
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new KBaseRelationEngineServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new KBaseRelationEngineServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}

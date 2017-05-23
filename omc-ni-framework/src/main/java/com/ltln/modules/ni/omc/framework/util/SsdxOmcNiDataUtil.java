package com.ltln.modules.ni.omc.framework.util;

import java.util.Map;

import com.ltln.modules.ni.omc.framework.start.NIUtil;
import com.ltln.modules.ni.omc.framework.util.Enum.CmEnum;

public class SsdxOmcNiDataUtil {
	  /**
	   * 根据AentId、全站Id得到Uid
	   * @param agentId
	   * @return 
	   */
	  public static String getUidByAgentOrWholeId(long uidValue,String cmType){

	    String hexStr="";
	    if(cmType.equals(CmEnum.NEL.getType())){
	      hexStr=getAgentShow(uidValue);
	    }else{
	      hexStr=Long.toHexString(uidValue);
	    }
	    
	    String uidFullShow=getFullShow(hexStr, 16);
	    
	    return getOMCUID()+cmType+uidFullShow;
	  }
	  
	  
	  /**
	   * 根据type、全站Id得到Uid
	   * @param trailId
	   * @param type 
	   * @param cmType
	   * @return 
	   */
	  public static String getUidByEthInfo(long trailId,String cmType){
	    
	    String trailHexStr=Long.toHexString(trailId);
	    String trailFullShow=getFullShow(trailHexStr, 12);
	    
	    return getOMCUID()+cmType+"0001"+trailFullShow;
	  }
	  
	  /**
	   * 根据Eth信息得到Uid
	   * @param type 1:实际Eth业务/2:copy Eth业务
	   * @param trailId
	   * @param v_i
	   * @param v_j
	   * @param cmType
	   * @return 
	   */
	  public static String getUidByEthInfo(long trailId,long v_i,long v_j,String cmType){
	    
	    String trailHexStr=Long.toHexString(trailId);
	    String trailFullShow=getFullShow(trailHexStr, 8);
	    
	    String viHexStr=Long.toHexString(v_i);
	    String viFullShow=getFullShow(viHexStr, 2);
	    
	    String vjHexStr=Long.toHexString(v_j);
	    String vjFullShow=getFullShow(vjHexStr, 2);
	    
	    return getOMCUID()+cmType+"0002"+trailFullShow+viFullShow+vjFullShow;
	  }
	  
	  /**
	   * 根据AentId、全站Id得到Uid
	   * @param trailId
	   * @param type    1:物理连接/2:虚拟连接
	   * @param cmType
	   * @return 
	   */
	  public static String getUidByTplInfo(long type,long trailId,String cmType){

	    String typeHexStr=Long.toHexString(type);
	    String typeFullShow=getFullShow(typeHexStr, 8);
	    
	    String trailHexStr=Long.toHexString(trailId);
	    String trailFullShow=getFullShow(trailHexStr, 8);
	    
	    return getOMCUID()+cmType+typeFullShow+trailFullShow;
	  }
	  
	  /**
	   * 根据Sbn信息得到Uid
	   * @param uidValue
	   * @param sbnType 3 psw/4 tdm
	   * @param cmType
	   * @return 
	   */
	  public static String getUidBySbnInfo(long uidValue,long sbnType,String cmType){

	    String typeStr=Long.toHexString(sbnType);

	    String typeFullShow=getFullShow(typeStr, 8);
	    
	    String hexStr=Long.toHexString(uidValue);

	    String uidFullShow=getFullShow(hexStr, 8);
	    
	    return getOMCUID()+typeFullShow+uidFullShow;
	  }
	  
	  public static String getUidByBandInfo(long trailid,long index,String cmType){
	    String wholeStr=Long.toHexString(trailid);
	    String wholeFullShow=getFullShow(wholeStr,8);
	    
	    String indexStr=Long.toHexString(index);
	    String indexFullShow=getFullShow(indexStr,8);
	    
	    return getOMCUID()+cmType+wholeFullShow+indexFullShow;
	  }
	  

	  
	  public static String getUidByRingIdFlag(long ringId,long ringFlag){
	    String idStr=Long.toHexString(ringId);
	    String idFullShow=getFullShow(idStr,4);
	    
	    String flagStr=Long.toHexString(ringFlag);
	    String flagFullShow=getFullShow(flagStr, 12);
	    
	    return getOMCUID()+CmEnum.MPI.getType()+idFullShow+flagFullShow;
	  }
	  
	  public static String getUidByMTLInfo(String agentShow,long ringTrailId,long num){
	    String ringIdStr=Long.toHexString(ringTrailId);
	    String ringIdFullShow=getFullShow(ringIdStr,4);
	    
	    String numStr=Long.toHexString(num);
	    String numFullShow=getFullShow(numStr,4);
	    
	    return getOMCUID()+CmEnum.MTL.getType()+agentShow+ringIdFullShow+numFullShow;
	    
	  }
	  public static String getUidByVitualPortInfo(long agentId,long slotId,long portType,long portNum,String cmType){
	    String agentHex=getAgentShow(agentId);
	    String slotHexStr=Long.toHexString(slotId);
	    String slotUid=getFullShow(slotHexStr, 2);
	    String typeHexStr=Long.toHexString(portType);
	    String typeUid=getFullShow(typeHexStr, 4);
	    String numHexStr=Long.toHexString(portNum);
	    String numUid=getFullShow(numHexStr, 2);
	    
	    return getOMCUID()+cmType+agentHex+slotUid+typeUid+numUid;
	  }
	  
	  public static String getUidByPWTInfo(int sequence,String type){
	    String indexHex=Long.toHexString(sequence);
	    String fullIndexStr=getFullShow(indexHex, 16);
	    return getOMCUID()+type+fullIndexStr;
	  }
	  

	  
	  /**
	   * 
	   * @param trailId
	   * @param type     3 Psw /4 tdm
	   * @param agentId
	   * @param cmType
	   * @return 
	   */
	  public static String getUidBySnnInfo(long trailId,long type,long agentId,String cmType){
	    
	    String agentHex=getAgentShow(agentId);
	    
	    String trailHexStr=Long.toHexString(trailId);
	    String trailUid=getFullShow(trailHexStr,4);
	    
	    String typeHexStr=Long.toHexString(type);
	    String typeUid=getFullShow(typeHexStr,4);
	    
	    String res=getOMCUID()+trailUid+typeUid+agentHex;
	    
	    return res;
	  }
	  

	  
	  private static String getUidByTnlRealation(long trailId,int tnlType,long index,String cmType){
	    
	    String trailIdStr=Long.toHexString(trailId);
	    String idFullShow=getFullShow(trailIdStr,4);
	    
	    String tnlTypeStr=Long.toHexString(tnlType);
	    String tnlTypeShow=getFullShow(tnlTypeStr,4);
	    
	    String indexStr=Long.toHexString(index);
	    String indexShow=getFullShow(indexStr,8);
	    
	    String uid=getOMCUID()+cmType+idFullShow+tnlTypeShow+indexShow;
	    
	    return uid;
	  }
	   public static String getOMCUID(){
		  return  NIUtil.getNiCityCode()+NIUtil.getNiFactoryCode()+NIUtil.getNiSkillCode()+NIUtil.getNiOmcId();
	   }

	  public static String getAgentShow(long agentId){
		    String agentF=getFullShow(String.valueOf(agentId), 12);
		    long aIp=Long.valueOf(agentF.substring(0,3));
		    long bIp=Long.valueOf(agentF.substring(3,6));
		    long cIp=Long.valueOf(agentF.substring(6,9));
		    long dIp=Long.valueOf(agentF.substring(9));
		    
		    String res=getFullShow(Long.toHexString(aIp), 2)+getFullShow(Long.toHexString(bIp), 2)+getFullShow(Long.toHexString(cIp), 2)+getFullShow(Long.toHexString(dIp), 2);
		    
		    return res;
		  }
	  /**
	   * 补全Uid 8位字符长度
	   * @param hexValue
	   * @return 
	   */
	  public static String getFullShow(String strValue,int fullLength){
	    String fullShow=strValue;
	    
	    if(fullShow.length()<fullLength){
	      int lenth=fullLength-fullShow.length();
	      for(int i=0;i<lenth;i++){
	        fullShow="0"+fullShow;
	      }
	    }
	    return fullShow;
	  }
	  /**
	   * 得到容器的Uid 前8位为agentId、后4位代表类型、最后4位代表index
	   * @param agentId
	   * @param index
	   * @param type 0000代表rack、0001代表shelf、0002代表Holder、0003代表Slot
	   * @return 
	   */
	  public static String getUidByCapatiyInfo(long agentId,long index,String type){
	    String agentHex=getAgentShow(agentId);
	    String indexHex=Long.toHexString(index);
	    
	    String uid=getFullShow(agentHex,8)+type+getFullShow(indexHex,4);
	    
	    
	    String res=getOMCUID()+CmEnum.EQH.getType()+uid;
	    if(type.equals("0003")){
	      res=getOMCUID()+CmEnum.CRD.getType()+uid;
	    }
	    
	    return res;
	  }

	
	  /**
	   * 得到端口、ac、xc、pw、Tunnel、槽位、板卡的Uid
	   * 前8位为agentId的Hex值、后8位为IfIndex的Hex值
	   * @param agentId
	   * @param cmType
	   * @param index
	   * @return 
	   */
	  public static String getUidByAgentAndIndex(long agentId,long index,String cmType){
	    
	    String agentHex=getAgentShow(agentId);
	    String indexHex=Long.toHexString(index);
	    
	    String uid=getFullShow(agentHex,8)+getFullShow(indexHex,8);
	    
	    String res=getOMCUID()+cmType+uid;
	    
	    return res;
	  }
	  /**
	   * 判断xc是否是最后一个Xc
	   * @param indexMap
	   * @param workXc
	   * @param xcIndex
	   * @return 
	   */
	  public static boolean jundgeIsEndXc(Map<String, String> indexMap, int workXc, int xcIndex) {
	    
	    int maxIndex=0;
	    if(indexMap!=null&&!indexMap.isEmpty()){
	      for(Map.Entry<String,String> entry:indexMap.entrySet()){
	         int isWorkXc=Integer.valueOf(entry.getValue().split("_")[0]);
	         if(workXc!=isWorkXc){
	           continue;
	         }
	         
	         int index=Integer.valueOf(entry.getValue().split("_")[1]);
	         if(index>maxIndex){
	           maxIndex=index;
	         }
	      }
	    }
	    return xcIndex==maxIndex;
	  }
	  
	  public static long getAgentIdByAgentShow(String agentShow) {
		    String aShow = Integer.valueOf(agentShow.substring(0, 2), 16).toString();
		    String bShow = Integer.valueOf(agentShow.substring(2, 4), 16).toString();
		    String cShow = Integer.valueOf(agentShow.substring(4, 6), 16).toString();
		    String dShow = Integer.valueOf(agentShow.substring(6), 16).toString();

		    long agentId = Long.valueOf(getFullShow(aShow, 3)+ getFullShow(bShow, 3)+ getFullShow(cShow, 3)+ getFullShow(dShow, 3));
		    
		    return agentId;
		  }
	  
	  public static String getAgentIpByAgentId(long agentId){
		    String agentF=getFullShow(String.valueOf(agentId), 12);
		    long aIp=Long.valueOf(agentF.substring(0,3));
		    long bIp=Long.valueOf(agentF.substring(3,6));
		    long cIp=Long.valueOf(agentF.substring(6,9));
		    long dIp=Long.valueOf(agentF.substring(9));
		    
		    String res=aIp+"."+bIp+"."+cIp+"."+dIp;
		    
		    return res;
		  }

		  
}

package network;

public class MessageIDs {
	public static final int ADDPOSTINGDATA_REQ = 0x00000001; // data : PostingModel
	public static final int POSTINGDATALIST_REQ = 0x00000002; // data : none
	public static final int DELPOSTINGDATA_REQ = 0x00000003; // data : int(id)
	public static final int UPDATEPOSTINGDATA_REQ = 0x00000004; //data : PostingModel
	public static final int LOGIN_REQ = 0x00000005; // data : LoginModel
	public static final int ADDMEMBER_REQ = 0x00000006; // data : MemberModel
	public static final int MEMBERLIST_REQ = 0x00000007; // data : none
	
	public static final int ADDPOSTRINGDATA_RES = 0x00010001; // data : PostingModel
	public static final int DELPOSTINGDATA_RES = 0x00010003; // data : int(id)
	public static final int UPDATEPOSTINGDATA_RES = 0x00010004; //data : PostingModel
	public static final int LOGIN_RES = 0x00010005; // data : LoginModel
	public static final int ADDMEMBER_RES = 0x00010006; // data : boolean
	public static final int ADDMEMBERLIST_RES = 0x00010007; // data : MemberModel
}

package auth;

public class AuthResponse{
	
	private int id;
    private String username;
    private String token;
    private boolean isAdmin;
    private boolean isParticipant;
    private boolean isMember;
    private boolean isManager;
    
    public AuthResponse(int id, String username, String token, 
    		boolean isAdmin, boolean isParticipant, 
    		boolean isMember, boolean isManager){
    	
    	this.id = id;
    	this.username = username;
    	this.token = token;
    	this.isAdmin = isAdmin;
    	this.isParticipant = isParticipant;
    	this.isMember = isMember;
    	this.isManager = isManager;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isParticipant() {
		return isParticipant;
	}

	public void setParticipant(boolean isParticipant) {
		this.isParticipant = isParticipant;
	}

	public boolean isMember() {
		return isMember;
	}

	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
    
    
}

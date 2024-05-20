package TaskTracker.database;

public enum Query {

    GET_USER_BY_USER_LOGIN("select * from users where userlogin = :userlogin"),
    GET_GROUP_BY_GROUP_ID("select * from usergroups where groupid = :groupid"),
    GET_TASK_BY_USER_TASK_ID("select * from usertasks where usertaskid = :usertaskid");

    private final String query;

    Query(String query) {
        this.query = query;
    }

    public String toString() {
        return query;
    }
}

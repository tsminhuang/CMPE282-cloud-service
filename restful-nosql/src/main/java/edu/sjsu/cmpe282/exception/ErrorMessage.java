package edu.sjsu.cmpe282.exception;

public class ErrorMessage {
    class MsgTemplae {
        public static final String MSG_RECORDS_IS_EMPTY = "collection are empty";
        public static final String MSG_RECORD_NOT_FOUND = "<id=%s> not found";
        public static final String MSG_RECORD_IS_EXIST = "<id=%s> is exist";
        public static final String MSG_ID_NOT_MATCH = "REQUEST: <id=%s> not match with JSON: <id=%s>";

    }

    private static String msgBuilder(String msgTemplate, Object... args) {
        return String.format(msgTemplate, args);
    }

    public static String msgEmptyCollection() {
        return MsgTemplae.MSG_RECORDS_IS_EMPTY;
    }

    public static String msgRecordNotFound(Object id) {
        return msgBuilder(MsgTemplae.MSG_RECORD_NOT_FOUND, id);
    }

    public static String msgRecordIsExist(Object id) {
        return msgBuilder(MsgTemplae.MSG_RECORD_IS_EXIST, id);
    }

    public static String msgNotMatchId(Object docId, Object jsonId) {
        return msgBuilder(MsgTemplae.MSG_ID_NOT_MATCH, docId, jsonId);
    }

}

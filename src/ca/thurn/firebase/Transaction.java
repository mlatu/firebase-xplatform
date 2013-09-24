package ca.thurn.firebase;

import com.google.gwt.core.client.JavaScriptObject;

public class Transaction {
	public static interface Handler {
		Result doTransaction(MutableData currentData);
		
		void onComplete(FirebaseError error, boolean committed, DataSnapshot currentData);
	}
	public static class Result {
		private final MutableData data;
		
		private Result() {
			this.data = null;
		}
		
		private Result( MutableData result) {
			this.data = result;
		}
		
		static JavaScriptObject getResultData(Result result) {
			return result.data.getNewData();
		}
		
		public boolean isSuccess() {
			return data != null;
		}
	}
	
	public static Result abort() {
		return new Result();
	}
	
	public static Result success(MutableData resultData) {
		return new Result(resultData);
	}
}

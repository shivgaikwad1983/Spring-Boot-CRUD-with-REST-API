package org.excercise.Exception;

public class ExceptionResponse {

		private String Code;
		private String Message;
		
		public ExceptionResponse(){
			
		}
		public ExceptionResponse(String _code,String _mesg){
			this.Code= _code;
			this.Message = _mesg;		
		}
		public String getCode() {
			return Code;
		}
		public void setCode(String _code) {
			this.Code = _code;
		}
		public String getMessage() {
			return Message;
		}
		public void setMessage(String _message) {
			this.Message = _message;
		}
}

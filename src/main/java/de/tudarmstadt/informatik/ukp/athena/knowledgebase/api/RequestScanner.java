package de.tudarmstadt.informatik.ukp.athena.knowledgebase.api;

import java.util.ArrayDeque;
import java.util.Deque;

import de.tudarmstadt.informatik.ukp.athena.knowledgebase.api.RequestToken.RequestTokenType;

public class RequestScanner {
	private char[] request;
	private int tokenStart = 0;
	private int currentIndex = 0;
	private String currentActualToken;
	private boolean previouslyEscaped = false;

	/**
	 * Sets up this scanner
	 * @param request The request to be scanned
	 */
	public RequestScanner(String request) {
		this(request.toCharArray());
	}

	/**
	 * Sets up this scanner
	 * @param request The request to be scanned, non-null
	 */
	public RequestScanner(char[] request) {
		this.request = request;
	}

	/**
	 * Converts the request string into a bunch of tokens that can be parsed
	 * @return A collection of tokens to be used for parsing
	 */
	public Deque<RequestToken> scan() {
		Deque<RequestToken> tokens = new ArrayDeque<>();

		//for each character
		while(currentIndex < request.length) {
			RequestTokenType type;

			//reset the actual token
			currentActualToken = "";
			type = scanToken(); //determine the type...
			tokens.add(new RequestToken(type, currentActualToken, tokenStart)); //...and add it to the token deque
			tokenStart = currentIndex;
		}

		//manually add the end token
		tokens.add(new RequestToken(RequestTokenType.END, "<end>", tokenStart));
		return tokens;
	}

	/**
	 * Appends the current character to the actual token and goes to the next character
	 * @return true, if there is a next character available, false if the end of the array has been reached
	 */
	private boolean appendCharacter() {
		//the current character is part of a bigger token, so add it to the actual token and increase the work index
		currentActualToken += request[currentIndex++]; //sneaky increment
		return currentIndex < request.length;
	}

	/**
	 * Categorizes the token at the current index and populates currentActualToken with the complete token
	 * @return the {@link RequestTokenType} of the current token
	 */
	private RequestTokenType scanToken() {
		//if it's alphabetic, there is a name of some sort,
		if(previouslyEscaped || Character.isAlphabetic(request[currentIndex])) { //previouslyEscaped: unknown symbols should be put into a string
			previouslyEscaped = false;
			appendCharacter();

			while(currentIndex < request.length && Character.isAlphabetic(request[currentIndex]) && appendCharacter()) //add remaining characters to complete token
				; //no operation while loop as the operations are all in the condition

			return RequestTokenType.NAME;
		}
		//if it's a digit, there is a number (duh!)
		else if(Character.isDigit(request[currentIndex])) {
			appendCharacter();

			while(currentIndex < request.length && Character.isDigit(request[currentIndex]) && appendCharacter())  //add remaining characters to complete token
				; //no operation while loop as the operations are all in the condition

			return RequestTokenType.NUMBER;
		}

		appendCharacter();

		//if none of the above applies, select the proper token type here
		switch(request[currentIndex - 1]) { //-1 because appendCharacter was already called and incremented currentIndex
			case '=': return RequestTokenType.ATTR_EQ;
			case '&': return RequestTokenType.ATTR_SEPARATOR;
			case ':': return RequestTokenType.ATTR_SPECIFIER;
			case '/': return RequestTokenType.HIERARCHY_SEPARATOR;
			case '`': previouslyEscaped = true; return RequestTokenType.ESCAPE; //backslash does not work in url, so use the backtick
			case '+': return RequestTokenType.SPACE;
			default: return RequestTokenType.ERROR;
		}
	}
}

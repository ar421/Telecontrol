package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import applications.sensehat.Client;
import applications.sensehat.Server;
import connectivity.ControllingCentre;
import connectivity.RemoteTerminalUnit;
import converters.Bit;
import converters.Converter;
import converters.Octet;
import exceptions.IllFormedOctetsException;
import exceptions.NotBinaryException;
import exceptions.StdException;

public class Testing {
	String testValue = "";
	@Before
	public void setup() {
		testValue = "00000001"
				+ "00011001"
				+ "00000000"
				+ "00000000"
				+ "00000000"
				+ "00000000"
				+ "00100100"
				+ "10000101"
				+ "00000100"
				+ "00000000"
				+ "00000000"
				+ "00000000"
				+ "00000000"
				+ "11110000"
				+ "01011000"
				+ "10101010"
				+ "01101010"
				+ "00000000"
				+ "10001100"
				+ "11010100"
				+ "10101110"
				+ "10000101"
				+ "10101010"
				+ "01010101"
				+ "00000000";
	}
	
	@Test
	public void t1_one_octet() {
		try {
			Converter.decode("5565555555555555");
		} catch (IllFormedOctetsException e) {
			fail("not enough data to form one octet");
		} catch (Exception e) {
			//fail("unknown error");
		}
	}

	@Test
	public void t2_binary() {
		try {
			Converter.decode("111111111111111111");
		} catch (NotBinaryException e) {
			fail("not binary data");
		} catch (Exception e) {
			//fail("unknown error");
		}
	}
	
	@Test
	public void t3_lexing() throws Exception {
		Bit[] bits = Converter.lex("00001111".toCharArray());
		Bit[] bits_veri = new Bit[]{Bit.ZERO,Bit.ZERO,Bit.ZERO,Bit.ZERO,Bit.ONE,Bit.ONE,Bit.ONE,Bit.ONE};
		boolean rtbool = true;
		for (int i = 0; i < 8; i++) {
			//System.out.println("b" + bits[i].toString() + " v" + bits_veri[i].toString());
			if(Integer.parseInt(bits[i].toString()) != Integer.parseInt(bits_veri[i].toString())) {
				rtbool = false;
			}
		}
		assertTrue(rtbool);
	}

	@Test
	public void t4_parsing() throws StdException, NullPointerException {
		Bit[] bits = Converter.lex("000011110101101000111100".toCharArray());
		Octet[] octets1, octets2;
		Octet octet1, octet2, octet3;
		octet1 = new Octet(Converter.lex("00001111".toCharArray()));
		octet2 = new Octet(Converter.lex("01011010".toCharArray()));
		octet3 = new Octet(Converter.lex("00111100".toCharArray()));
		octets1 = Converter.parse(bits);
		octets2 = new Octet[]{octet1,octet2,octet3};
		
		
		if (octets1.length != octets2.length) {
			fail();
		}
		for (int i = 0; i < 3; i++) {
			//System.out.println("o" + octets1[i].toString() + " v" + octets2[i].toString());
			if(!octets1[i].equals(octets2[i])) {
				fail();
			}
		}
	}
	
	@Test
	public void t5_decimal() throws StdException, NullPointerException {
		Bit[] bits = Converter.lex("10001000".toCharArray());
		Octet octet = new Octet(bits);
		if (Octet.getDecimal(octet.getBits()) != 136) {
			System.out.println(Octet.getDecimal(octet.getBits()) + "      \n" + octet.toString());
			fail();
		}
	}
	
	@Test
	public void t6_decoder() throws Exception {
		System.out.println(Converter.decode(testValue).toString());
	}
	
	@Test
	public void t7_encoder() throws Exception {
		//byte[] test = Converter.encode(Converter.decode(testValue));
		assertTrue(Converter.encode(Converter.decode(testValue)).equals(testValue));
	}
	
	@Test
	public void t8a_server() {
		RemoteTerminalUnit.main(null);
	}
	
	@Test
	public void t8b_client() {
		ControllingCentre.main(new String[] {"127.0.0.1"});
	}
	
	@Test
	public void t9_getBits() {
		assertTrue(Octet.getBits(1, 8).equals("00000001"));
	}
	
	@Test
	public void t10a_send() {
		Client c = new Client("127.0.0.1");
		c.launch();
		assertTrue(true);
	}
	
	@Test
	public void t10b_receive() {
		Server s = new Server();
		s.launch();
	}
}

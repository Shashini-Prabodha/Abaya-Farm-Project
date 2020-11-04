package lk.abayafarm.pos.util.impl;

import lk.abayafarm.pos.util.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexImpl implements Regex {
    @Override
    public boolean regex(String code, String text)  {
        Pattern pattern=Pattern.compile("^"+code+"$");
        Matcher matcher=pattern.matcher(text);
        return matcher.matches();

    }
}

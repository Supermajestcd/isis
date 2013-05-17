package org.apache.isis.viewer.wicket.ui.components.scalars.jdkdates;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;
import org.apache.isis.viewer.wicket.model.isis.WicketViewerSettings;
import org.apache.isis.viewer.wicket.ui.components.scalars.jodatime.DateConverterForJodaLocalDateTime;

public class DateConverterForJavaUtilDateTest {

    @Rule
    public JUnitRuleMockery2 context = JUnitRuleMockery2.createFor(Mode.INTERFACES_AND_CLASSES);

    @Mock
    private WicketViewerSettings settings;

    @Before
    public void setUp() throws Exception {
        context.checking(new Expectations() {
            {
                allowing(settings).getDatePattern();
                will(returnValue("yyyy-MM-dd"));
                allowing(settings).getDateTimePattern();
                will(returnValue("yyyy-MM-dd HH:mm"));
                allowing(settings).getDatePickerPattern();
                will(returnValue("yy-mm-dd"));
            }
        });
    }
    
    @Test
    public void roundtripWhenParsingDateFormat() {
        final DateConverterForJavaUtilDate converter = new DateConverterForJavaUtilDate(settings, 0);
        final java.util.Date dt = converter.convertToObject("2013-05-11", null);
        assertThat(dt, is(newJavaUtilDate(2013, 5, 11)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }
    
    @Test
    public void roundtripWhenParsingDateTimeFormat() {
        final DateConverterForJavaUtilDate converter = new DateConverterForJavaUtilDate(settings, 0);
        final Date dt = converter.convertToObject("2013-05-11 00:00", null);
        assertThat(dt, is(newJavaUtilDate(2013, 5, 11)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }
    
    @Test
    public void roundtripWhenParsingDateFormatWithAdjustBy() {
        final DateConverterForJavaUtilDate converter = new DateConverterForJavaUtilDate(settings, -1);
        final Date dt = converter.convertToObject("2013-05-11", null);
        assertThat(dt, is(newJavaUtilDate(2013, 5, 12)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }

    @Test
    public void roundtripWhenParsingDateTimeFormatWithAdjustBy() {
        final DateConverterForJavaUtilDate converter = new DateConverterForJavaUtilDate(settings, -1);
        final Date dt = converter.convertToObject("2013-05-11 00:00", null);
        assertThat(dt, is(newJavaUtilDate(2013, 5, 12)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }

    @SuppressWarnings("deprecation")
    private java.util.Date newJavaUtilDate(int yyyy, int mm, int dd) {
        return new java.util.Date(yyyy-1900, mm-1, dd);
    }

}

package org.apache.isis.viewer.wicket.ui.components.scalars.jodatime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2;
import org.apache.isis.core.unittestsupport.jmocking.JUnitRuleMockery2.Mode;
import org.apache.isis.viewer.wicket.model.isis.WicketViewerSettings;
import org.apache.isis.viewer.wicket.ui.components.scalars.jodatime.DateConverterForJodaLocalDate;

public class DateConverterForJodaDateTimeTest {

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
        final DateConverterForJodaDateTime converter = new DateConverterForJodaDateTime(settings, 0);
        final DateTime dt = converter.convertToObject("2013-05-11", null);
        assertThat(dt, is(new DateTime(2013, 05, 11, 0, 0)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }
    
    @Test
    public void roundtripWhenParsingDateTimeFormat() {
        final DateConverterForJodaDateTime converter = new DateConverterForJodaDateTime(settings, 0);
        final DateTime dt = converter.convertToObject("2013-05-11 00:00", null);
        assertThat(dt, is(new DateTime(2013, 05, 11, 0, 0)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }
    
    @Test
    public void roundtripWhenParsingDateFormatWithAdjustBy() {
        final DateConverterForJodaDateTime converter = new DateConverterForJodaDateTime(settings, -1);
        final DateTime dt = converter.convertToObject("2013-05-11", null);
        assertThat(dt, is(new DateTime(2013, 05, 12, 0, 0)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }

    @Test
    public void roundtripWhenParsingDateTimeFormatWithAdjustBy() {
        final DateConverterForJodaDateTime converter = new DateConverterForJodaDateTime(settings, -1);
        final DateTime dt = converter.convertToObject("2013-05-11 00:00", null);
        assertThat(dt, is(new DateTime(2013, 05, 12, 0, 0)));
        
        final String str = converter.convertToString(dt, null);
        assertThat(str, is("2013-05-11 00:00"));
    }
    

}

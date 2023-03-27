package spaland;


import java.time.LocalDateTime;

public class CurrentDateTime {

        // 현재 날짜/시간
        public  LocalDateTime now = LocalDateTime.now();

        // 년, 월(문자열, 숫자), 일(월 기준, 년 기준), 요일(문자열, 숫자), 시, 분, 초 구하기
        public int year = now.getYear();  // 연도
        public String month = now.getMonth().toString();  // 월(문자열)
        public  int monthValue = now.getMonthValue();  // 월(숫자)
        public int dayOfMonth = now.getDayOfMonth();  // 일(월 기준)
        public int dayOfYear = now.getDayOfYear();  // 일(년 기준)
        public String dayOfWeek = now.getDayOfWeek().toString();  // 요일(문자열)
        public int dayOfWeekValue = now.getDayOfWeek().getValue();  // 요일(숫자)
        public int hour = now.getHour();
        public int minute = now.getMinute();
        public int second = now.getSecond();
        // 현재 날짜/시간 출력


}
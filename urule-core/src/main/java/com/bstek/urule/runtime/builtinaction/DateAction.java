//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

@ActionBean(
        name = "日期"
)
public class DateAction {
    public DateAction() {
    }

    @ActionMethod(
            name = "取指定月份天数"
    )
    @ActionMethodParameter(
            names = {"开始日期", "结束日期", "月份"}
    )
    public int buildIncludeMonthDays(Object var1, Object var2, String var3) {
        return this.a(var1, var2, var3, true);
    }

    @ActionMethod(
            name = "取非指定月份天数"
    )
    @ActionMethodParameter(
            names = {"开始日期", "结束日期", "月份"}
    )
    public int buildExcludeMonthDays(Object var1, Object var2, String var3) {
        return this.a(var1, var2, var3, false);
    }

    private int a(Object var1, Object var2, String var3, boolean var4) {
        if (var1 == null) {
            throw new RuleException("开始日期不能为空！");
        } else if (var2 == null) {
            throw new RuleException("开始日期不能为空！");
        } else {
            Date var5 = this.a(var1);
            Date var6 = this.a(var2);
            if (var5.compareTo(var6) > 0) {
                throw new RuleException("开始日期必须要小于结束日期！");
            } else {
                int var7 = 0;
                int var8 = 0;
                String[] var9 = var3.split(",");

                Calendar var11;
                for(List var10 = Arrays.asList(var9); var5.compareTo(var6) <= 0; var5 = var11.getTime()) {
                    var11 = Calendar.getInstance();
                    var11.setTime(var5);
                    int var12 = var11.get(2) + 1;
                    if (var10.contains(String.valueOf(var12))) {
                        if (var4) {
                            ++var7;
                        }
                    } else if (!var4) {
                        ++var8;
                    }

                    var11.add(5, 1);
                }

                if (var4) {
                    return var7;
                } else {
                    return var8;
                }
            }
        }
    }

    private Date a(Object var1) {
        SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd");
        if (var1 instanceof Date) {
            Date var9 = (Date)var1;
            String var4 = var2.format(var9);

            try {
                return var2.parse(var4);
            } catch (ParseException var7) {
                throw new RuleException(var7);
            }
        } else {
            String var3 = var1.toString();

            try {
                return var2.parse(var3);
            } catch (ParseException var8) {
                var2 = new SimpleDateFormat("yyyy/MM/dd");

                try {
                    return var2.parse(var3);
                } catch (ParseException var6) {
                    throw new RuleException("不能将[" + var1 + "]解析成日期");
                }
            }
        }
    }

    @ActionMethod(
            name = "解析字符串为日期"
    )
    @ActionMethodParameter(
            names = {"日期字符串", "格式"}
    )
    public Date formatString(String var1, String var2) {
        if (StringUtils.isBlank(var1)) {
            return null;
        } else {
            SimpleDateFormat var3 = new SimpleDateFormat(var2);

            try {
                return var3.parse(var1);
            } catch (ParseException var5) {
                throw new RuleException(var5);
            }
        }
    }

    @ActionMethod(
            name = "当前日期"
    )
    @ActionMethodParameter(
            names = {}
    )
    public Date getDate() {
        return new Date();
    }

    @ActionMethod(
            name = "格式化日期"
    )
    @ActionMethodParameter(
            names = {"目标日期", "格式"}
    )
    public String format(Date var1, String var2) {
        if (var1 == null) {
            return null;
        } else {
            SimpleDateFormat var3 = new SimpleDateFormat(var2);
            return var3.format(var1);
        }
    }

    @ActionMethod(
            name = "加日期"
    )
    @ActionMethodParameter(
            names = {"目标日期", "年数", "月数", "天数", "小时", "分钟", "秒数"}
    )
    public Date addDate(Date var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var8 = Calendar.getInstance();
            var8.setTime(var1);
            var8.add(1, var2);
            var8.add(2, var3);
            var8.add(5, var4);
            var8.add(11, var5);
            var8.add(12, var6);
            var8.add(13, var7);
            return var8.getTime();
        }
    }

    @ActionMethod(
            name = "日期加年"
    )
    @ActionMethodParameter(
            names = {"目标日期", "年数"}
    )
    public Date addDateForYear(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(1, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "日期加月"
    )
    @ActionMethodParameter(
            names = {"目标日期", "月数"}
    )
    public Date addDateForMonth(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(2, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "日期加天"
    )
    @ActionMethodParameter(
            names = {"目标日期", "天数"}
    )
    public Date addDateForDay(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(5, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "日期加小时"
    )
    @ActionMethodParameter(
            names = {"目标日期", "小时数"}
    )
    public Date addDateForHour(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(11, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "日期加分钟"
    )
    @ActionMethodParameter(
            names = {"目标日期", "分钟数"}
    )
    public Date addDateForMinute(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(12, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "日期加秒"
    )
    @ActionMethodParameter(
            names = {"目标日期", "秒数"}
    )
    public Date addDateForSecond(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(13, var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期"
    )
    @ActionMethodParameter(
            names = {"目标日期", "年数", "月数", "天数", "小时", "分钟", "秒数"}
    )
    public Date subDate(Date var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var8 = Calendar.getInstance();
            var8.setTime(var1);
            var8.add(1, -var2);
            var8.add(2, -var3);
            var8.add(5, -var4);
            var8.add(11, -var5);
            var8.add(12, -var6);
            var8.add(13, -var7);
            return var8.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减年"
    )
    @ActionMethodParameter(
            names = {"目标日期", "年数"}
    )
    public Date subDateForYear(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(1, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减月"
    )
    @ActionMethodParameter(
            names = {"目标日期", "月数"}
    )
    public Date subDateForMonth(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(2, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减天"
    )
    @ActionMethodParameter(
            names = {"目标日期", "天数"}
    )
    public Date subDateForDay(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(5, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减小时"
    )
    @ActionMethodParameter(
            names = {"目标日期", "小时"}
    )
    public Date subDateForHour(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(11, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减分钟"
    )
    @ActionMethodParameter(
            names = {"目标日期", "分钟"}
    )
    public Date subDateForMinute(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(12, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "减日期减秒"
    )
    @ActionMethodParameter(
            names = {"目标日期", "秒数"}
    )
    public Date subDateForSecond(Date var1, int var2) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            var3.add(13, -var2);
            return var3.getTime();
        }
    }

    @ActionMethod(
            name = "取年份"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getYear(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(1);
        }
    }

    @ActionMethod(
            name = "取月份"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getMonth(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(2);
        }
    }

    @ActionMethod(
            name = "取星期"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getWeek(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(7);
        }
    }

    @ActionMethod(
            name = "取天"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getay(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(5);
        }
    }

    @ActionMethod(
            name = "取小时"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getHour(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(11);
        }
    }

    @ActionMethod(
            name = "取分钟"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getMinute(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(12);
        }
    }

    @ActionMethod(
            name = "取秒"
    )
    @ActionMethodParameter(
            names = {"目标日期"}
    )
    public Object getSecond(Date var1) {
        if (var1 == null) {
            return null;
        } else {
            Calendar var2 = Calendar.getInstance();
            var2.setTime(var1);
            return var2.get(13);
        }
    }

    @ActionMethod(
            name = "日期相减返回毫秒"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifMillSecond(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return var5 - var7;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回秒"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifSecond(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return (var5 - var7) / 1000L;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回分钟"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifMinute(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return (var5 - var7) / 60000L;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回小时"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifHour(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return (var5 - var7) / 3600000L;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回天"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifDay(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return (var5 - var7) / 86400000L;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回星期"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifWeek(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            long var5 = var3.getTimeInMillis();
            long var7 = var4.getTimeInMillis();
            return (var5 - var7) / 604800000L;
        } else {
            return null;
        }
    }

    @ActionMethod(
            name = "日期相减返回月"
    )
    @ActionMethodParameter(
            names = {"日期", "减去的日期"}
    )
    public Object dateDifMonth(Date var1, Date var2) {
        if (var1 != null && var2 != null) {
            Calendar var3 = Calendar.getInstance();
            var3.setTime(var1);
            Calendar var4 = Calendar.getInstance();
            var4.setTime(var2);
            int var5 = var3.get(1);
            int var6 = var4.get(1);
            int var7 = var3.get(2);
            int var8 = var4.get(2);
            int var9 = 12 * (var5 - var6) + (var7 - var8);
            return var9;
        } else {
            return null;
        }
    }
}

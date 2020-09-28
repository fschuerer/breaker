package de.fs.breaker.ping;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author XLKAFR
 */
public class CircuitBreakerMetric {

    @JsonbProperty("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.callsFailed.total")
    public long callsFailedTotal;
    @JsonbProperty("ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.callsSucceeded.total")
    public long callsSucceededTotal;

//    public long "ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.closed.total": 192587093600,
//    public long "ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.halfOpen.total": 0,
//    public long "ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.circuitbreaker.open.total": 0,
//    public long "ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.invocations.failed.total": 5,
//    public long "ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.invocations.total": 6,
//    public long 
//"ft.de.fs.breaker.ping.control.ServiceCaller.callTheService.retry.callsSucceededNotRetried.total": 1
}

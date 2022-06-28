package ai.ibriz.dummy;

import com.iconloop.score.token.irc31.IRC31Basic;
import finance.omm.utils.math.MathUtils;
import java.math.BigInteger;
import java.util.Map;
import score.Address;
import score.Context;
import score.annotation.External;

public class Dex extends IRC31Basic {


    @External
    public void mint(BigInteger _id, BigInteger _supply) {
        final Address caller = Context.getCaller();
        Context.require(caller.equals(Context.getOwner()), "Not an owner");
        Context.require(_supply.compareTo(BigInteger.ZERO) > 0, "Supply should be positive");

        super._mint(caller, _id, _supply);
    }

    @External
    public void mintTo(BigInteger _id, Address _account, BigInteger _supply) {
        final Address caller = Context.getCaller();
        Context.require(caller.equals(Context.getOwner()), "Not an owner");
        Context.require(_supply.compareTo(BigInteger.ZERO) > 0, "Supply should be positive");

        super._mint(_account, _id, _supply);
    }

    @External(readonly = true)
    public Map<String, ?> getPoolStats(BigInteger _id) {
        BigInteger quoteDecimals = BigInteger.valueOf(18);
        BigInteger baseDecimals = BigInteger.valueOf(18);
        if (_id.equals(BigInteger.valueOf(6))) {
            quoteDecimals = BigInteger.valueOf(6);
        }
        BigInteger average = (quoteDecimals.add(baseDecimals)).divide(BigInteger.valueOf(2));
        return Map.of(
                "base", BigInteger.valueOf(1000).multiply(MathUtils.pow(BigInteger.TEN, baseDecimals.intValue())),
                "base_decimals", baseDecimals,
                "base_token", "cx1a29259a59f463a67bb2ef84398b30ca56b5830a",
                "min_quote", BigInteger.ZERO,
                "name", "OMM/ABC",
                "price", BigInteger.valueOf(13).multiply(MathUtils.ICX),
                "quote", BigInteger.valueOf(1000).multiply(MathUtils.pow(BigInteger.TEN, quoteDecimals.intValue())),
                "quote_decimals", quoteDecimals,
                "quote_token", "cxae3034235540b924dfcc1b45836c293dcc82bfb7",
                "total_supply", BigInteger.valueOf(1000).multiply(MathUtils.pow(BigInteger.TEN, average.intValue()))
        );
    }


    @External(readonly = true)
    public BigInteger getPriceByName(String _name) {
        return BigInteger.valueOf(13).multiply(MathUtils.ICX);
    }

    @External(readonly = true)
    public BigInteger lookupPid(String _name) {
        return BigInteger.valueOf(6);
    }

    @External(readonly = true)
    public BigInteger getBalnPrice() {
        return BigInteger.valueOf(19).multiply(MathUtils.ICX);
    }

}

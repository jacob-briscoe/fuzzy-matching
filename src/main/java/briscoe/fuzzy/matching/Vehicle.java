package briscoe.fuzzy.matching;

import static me.xdrop.fuzzywuzzy.FuzzySearch.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vehicle {

    private final String make;
    private final String model;

    public static Vehicle of(final String make, final String model) {
        return new Vehicle(make, model);
    }

    public boolean isSimilarTo(final Vehicle another) {
        return makeIsSimilarTo(another) && modelIsSimilarTo(another);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("make", make)
                .append("model", model).toString();
    }

    private Vehicle(final String make, final String model) {
        this.make = make;
        this.model = model;
    }

    private String prepareForComparison(final String input) {
        return input.trim().toLowerCase();
    }

    private boolean makeIsSimilarTo(final Vehicle another) {
        return compareTheMake(another.make).isSimilarEnough();
    }

    private FuzzySearchResult compareTheMake(final String make) {
        return compare(prepareForComparison(this.make), prepareForComparison(make));
    }

    private boolean modelIsSimilarTo(final Vehicle another) {
        return compareTheModel(another.model).isSimilarEnough();
    }

    private FuzzySearchResult compareTheModel(final String model) {
        return compare(prepareForComparison(this.model), prepareForComparison(model));
    }

    private FuzzySearchResult compare(final String input, final String other) {
        return new FuzzySearchResult.Builder()
                .setSimpleRatio(ratio(input, other))
                .setPartialRatio(partialRatio(input, other))
                .setTokenSortPartialRatio(tokenSortPartialRatio(input, other))
                .setTokenSortRatio(tokenSortRatio(input, other))
                .setTokenSetRatio(tokenSetRatio(input, other))
                .setTokenSetPartialRatio(tokenSetPartialRatio(input, other))
                .setTokenWeightedRatio(weightedRatio(input, other))
                .build();
    }

    private static class FuzzySearchResult {

        private static final int MATCH_RATIO_THRESHOLD = 65;
        private final int TOTAL_NUM_SEARCH_RESULT_RATIOS = 7;
        private final int simpleRatio, partialRatio,
                tokenSortPartialRatio, tokenSortRatio,
                tokenSetRatio, tokenSetPartialRatio, tokenWeightedRatio;

        public boolean isSimilarEnough(){
            return average() >= MATCH_RATIO_THRESHOLD;
        }

        public double average() {
            return total() / TOTAL_NUM_SEARCH_RESULT_RATIOS;
        }

        private int total() {
            return simpleRatio + partialRatio + tokenSortPartialRatio + tokenSortRatio + tokenSetRatio + tokenSetPartialRatio + tokenWeightedRatio;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("simpleRatio", simpleRatio)
                    .append("partialRatio", partialRatio)
                    .append("tokenSortPartialRatio", tokenSortPartialRatio)
                    .append("tokenSortRatio", tokenSortRatio)
                    .append("tokenSetRatio", tokenSetRatio)
                    .append("tokenSetPartialRatio", tokenSetPartialRatio)
                    .append("tokenWeightedRatio", tokenWeightedRatio)
                    .toString();
        }

        private static class Builder {

            private int simpleRatio, partialRatio,
                    tokenSortPartialRatio, tokenSortRatio,
                    tokenSetRatio, tokenSetPartialRatio, tokenWeightedRatio;

            public Builder setSimpleRatio(final int simpleRatio) {
                this.simpleRatio = simpleRatio;
                return this;
            }

            public Builder setPartialRatio(final int partialRatio) {
                this.partialRatio = partialRatio;
                return this;
            }

            public Builder setTokenSortPartialRatio(final int tokenSortPartialRatio) {
                this.tokenSortPartialRatio = tokenSortPartialRatio;
                return this;
            }

            public Builder setTokenSortRatio(final int tokenSortRatio) {
                this.tokenSortRatio = tokenSortRatio;
                return this;
            }

            public Builder setTokenSetRatio(final int tokenSetRatio) {
                this.tokenSetRatio = tokenSetRatio;
                return this;
            }

            public Builder setTokenSetPartialRatio(final int tokenSetPartialRatio) {
                this.tokenSetPartialRatio = tokenSetPartialRatio;
                return this;
            }

            public Builder setTokenWeightedRatio(final int tokenWeightedRatio) {
                this.tokenWeightedRatio = tokenWeightedRatio;
                return this;
            }

            public FuzzySearchResult build() {
                return new FuzzySearchResult(this);
            }
        }

        private FuzzySearchResult() {
            this(new Builder());
        }

        private FuzzySearchResult(final Builder builder) {
            this.simpleRatio = builder.simpleRatio;
            this.partialRatio = builder.partialRatio;
            this.tokenSortPartialRatio = builder.tokenSortPartialRatio;
            this.tokenSortRatio = builder.tokenSortRatio;
            this.tokenSetRatio = builder.tokenSetRatio;
            this.tokenSetPartialRatio = builder.tokenSetPartialRatio;
            this.tokenWeightedRatio = builder.tokenWeightedRatio;
        }
    }

}

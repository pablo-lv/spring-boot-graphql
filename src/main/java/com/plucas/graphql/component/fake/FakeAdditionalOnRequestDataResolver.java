package com.plucas.graphql.component.fake;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.plucas.graphql.generated.DgsConstants;
import org.springframework.web.bind.annotation.RequestHeader;

@DgsComponent
public class FakeAdditionalOnRequestDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AdditionalOnRequest)
    public String additionalOnRequest(
            @RequestHeader(name = "optionalHeader", required = false) String optionalHeader,
            @RequestHeader(name = "mandatoryHeader", required = true) String mandatoryHeader,
            @RequestHeader(name = "optionalParam", required = false)String optionalParam,
            @RequestHeader(name = "mandatoryParam", required = true)String mandatoryParam) {

        var sb = new StringBuilder();

        sb.append("Optional header: " + optionalHeader);
        sb.append(", ");
        sb.append("Mandatory header: " + mandatoryHeader);
        sb.append(", ");
        sb.append("Optional param: " + optionalParam);
        sb.append(", ");
        sb.append("Mandatory header: " + mandatoryParam);

        return sb.toString();
    }
}

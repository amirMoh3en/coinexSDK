package com.coinex.sdk.websocket.event;

import com.coinex.sdk.rest.response.SnapshotResponse;
import lombok.Data;

/**
 * Created by Reeta on 2019-05-21
 */
@Data
public class SnapshotEvent {

    private String sequence;

    private SnapshotResponse data;

}

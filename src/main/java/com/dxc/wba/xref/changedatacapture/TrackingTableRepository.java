package com.dxc.wba.xref.changedatacapture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingTableRepository extends JpaRepository<TrackingTableEntry, Long> {

    @Query(value = "SELECT * FROM xref.tracking_table where change_type='INSERT' ORDER BY change_time DESC LIMIT 1", nativeQuery = true)
    TrackingTableEntry findLastUpdate();
}

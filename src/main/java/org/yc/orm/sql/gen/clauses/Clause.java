package org.yc.orm.sql.gen.clauses;

import org.yc.orm.sql.Statement;
import org.yc.orm.util.meta.Meta;
import org.yc.orm.util.meta.TableMeta;

public class Clause<T> {
    private final TableMeta<T> meta;

    public Clause(TableMeta<T> meta) {
        this.meta = meta;
    }

    public Clause(Meta<T> meta) {
        this.meta = new TableMeta<>(meta);
    }

    public TableMeta<T> getMeta() {
        return meta;
    }

    public Statement generate() throws IllegalAccessException {
        throw new UnsupportedOperationException("Not implemented");
    }
}

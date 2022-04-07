package org.yc.orm.sql.gen.clauses;

import org.yc.orm.sql.Statement;
import org.yc.orm.util.meta.Meta;
import org.yc.orm.util.meta.TableMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Update<T> extends Clause<T> {
    public Update(Meta<T> meta) {
        super(meta);
        assert meta.hasValue();
    }

    @Override
    public Statement generate() throws IllegalAccessException {
        final TableMeta<T> meta = getMeta();
        final ArrayList<Statement> statements = new ArrayList<>();
        statements.add(Statement.of("UPDATE `%s` SET %s".formatted(
          meta.getTableName(),
          Arrays
            .stream(meta.getFields())
            .map(field -> "`%s` = ?".formatted(field.getName()))
            .collect(Collectors.joining(", "))
        ), meta.getFieldValues()));
        final Field id = meta.getIdColumn().orElseThrow();
        id.setAccessible(true);
        final Where<T>.WhereBuilder builder = new Where<>(meta).new WhereBuilder(
          statements);
        return builder.byId(id.get(meta.getValue())).end();
    }
}

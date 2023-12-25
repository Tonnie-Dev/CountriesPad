import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.uxstate.ui.theme.LocalSpacing

@Composable
fun SettingsItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @StringRes subTitle: Int,
    @DrawableRes icon:Int,
    onClickSetting: () -> Unit
) {

    val spacing = LocalSpacing.current


    Row(
            modifier = modifier
                    .clickable(onClick = onClickSetting)
                    .padding(spacing.spaceSmall)
                    .fillMaxWidth()
    ) {

        Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(spacing.spaceMedium)
        )

        Column(modifier = Modifier.padding(spacing.spaceMedium)) {


            Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge
            )


            Text(
                    text = stringResource(id = subTitle),
                    style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}